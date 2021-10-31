package com.avelycure.moviefan.presentation.home

import android.app.SearchManager
import android.content.Context
import android.graphics.Color
import android.net.ConnectivityManager
import android.os.Build
import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.avelycure.moviefan.R
import com.avelycure.moviefan.common.Constants
import com.avelycure.moviefan.data.remote.adapters.MovieAdapter
import com.avelycure.moviefan.di.PopularMovieAdapterFactory
import com.avelycure.moviefan.domain.models.Movie
import com.avelycure.moviefan.presentation.app_info.AppInfo
import com.avelycure.moviefan.presentation.movie_info.MovieInfoFragment
import com.avelycure.moviefan.utils.getQueryChangeStateFlow
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {
    @Inject
    lateinit var movieAdapterFactory: PopularMovieAdapterFactory
    lateinit var movieAdapter: MovieAdapter

    private val homeViewModel: HomeViewModel by viewModels()

    private lateinit var searchView: SearchView
    private lateinit var closeBtn: ImageView
    private lateinit var rvPopularMovie: RecyclerView
    private lateinit var loadingProgressBar: ProgressBar
    private lateinit var btnRetry: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_popular_movies, container, false)
        initViewElements(view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView(btnRetry)
        fetchPopularMovies()
    }

    @FlowPreview
    @ExperimentalCoroutinesApi
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        if ((activity as AppCompatActivity).supportActionBar?.title == Constants.POPULAR_MOVIE_TITLE_DEFAULT) {
            menu.clear()
            inflater.inflate(R.menu.toolbar_menu, menu)
            initSearchView(menu)

            lifecycleScope.launch {
                searchView.getQueryChangeStateFlow()
                    .debounce(500)
                    .filter { query ->
                        return@filter query.isNotEmpty()
                    }
                    .distinctUntilChanged()
                    .flatMapLatest { query ->
                        homeViewModel.searchMovie(query)
                    }
                    .flowOn(Dispatchers.Main)
                    .collectLatest {
                        movieAdapter.submitData(it)
                    }
            }
        }
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == R.id.action_info) {
            activity?.supportFragmentManager
                ?.beginTransaction()
                ?.addToBackStack(Constants.POPULAR_MOVIE_TAG)
                ?.add(R.id.fragment_container, AppInfo())
                ?.commit()
            true
        } else {
            super.onOptionsItemSelected(item)
        }
    }

    private fun initRecyclerView(view: View) {
        rvPopularMovie.layoutManager = LinearLayoutManager(view.context)

        movieAdapter = movieAdapterFactory.create { movie ->
            if (isOnline())
                createYouTubeFragment(movie)
            else
                showNoInternetConnectionError(view)
        }

        movieAdapter.addLoadStateListener { loadState ->
            if (loadState.refresh is LoadState.Loading)
                loadingProgressBar.visibility = View.VISIBLE
            else {
                loadingProgressBar.visibility = View.GONE

                val errorState = when {
                    loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
                    loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                    loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
                    else -> null
                }
                errorState?.let {
                    if (movieAdapter.itemCount == 0)
                        btnRetry.visibility = View.VISIBLE
                    loadingProgressBar.visibility = View.GONE
                    showNoInternetConnectionError(btnRetry)
                }
            }
        }

        rvPopularMovie.adapter = movieAdapter.withLoadStateFooter(
            footer = MovieLoadStateAdapter { movieAdapter.retry() }
        )
    }

    private fun initViewElements(view: View) {
        setHasOptionsMenu(true)

        (activity as AppCompatActivity).setSupportActionBar(view.findViewById(R.id.pm_toolbar))
        (activity as AppCompatActivity).supportActionBar?.title =
            Constants.POPULAR_MOVIE_TITLE_DEFAULT

        rvPopularMovie = view.findViewById(R.id.rv_popular_movies)
        loadingProgressBar = view.findViewById(R.id.fragment_pm_pb)
        btnRetry = view.findViewById(R.id.main_btn_restart)
        btnRetry.setOnClickListener {
            if (isOnline()) {
                btnRetry.visibility = View.INVISIBLE
                fetchPopularMovies()
            } else
                showNoInternetConnectionError(view)
        }
    }

    private fun initSearchView(menu: Menu) {
        val searchManager =
            (activity as AppCompatActivity).getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView = menu.findItem(R.id.search_view).actionView as SearchView
        searchView.setSearchableInfo(searchManager.getSearchableInfo((activity as AppCompatActivity).componentName))
        searchView.setIconifiedByDefault(false)

        (menu.findItem(R.id.search_view) as MenuItem).setOnActionExpandListener(object :
            MenuItem.OnActionExpandListener {
            override fun onMenuItemActionExpand(p0: MenuItem?): Boolean {
                return true
            }
            override fun onMenuItemActionCollapse(p0: MenuItem?): Boolean {
                (activity as AppCompatActivity).invalidateOptionsMenu()
                fetchPopularMovies()
                return true
            }
        })
    }

    private fun createYouTubeFragment(movie: Movie) {
        val fragmentInfo = MovieInfoFragment()
        fragmentInfo.arguments = bundleOf(
            Constants.ID_KEY to movie.movieId,
            Constants.MOVIE_TITLE to movie.title
        )
        activity?.supportFragmentManager
            ?.beginTransaction()
            ?.addToBackStack(Constants.POPULAR_MOVIE_TAG)
            ?.add(R.id.fragment_container, fragmentInfo)
            ?.commit()
    }

    private fun fetchPopularMovies() {
        lifecycleScope.launch {
            homeViewModel
                .getPopularMovies()
                .collectLatest {
                    movieAdapter.submitData(it)
                }
        }
    }

    private fun isOnline(): Boolean = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        (activity?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).activeNetwork != null
    } else {
        true
    }

    private fun showNoInternetConnectionError(view: View) {
        val sb = Snackbar.make(
            requireContext(),
            view,
            Constants.NO_INTERNET_CONNECTION,
            Snackbar.LENGTH_SHORT
        )
        (sb.view as Snackbar.SnackbarLayout).findViewById<TextView>(R.id.snackbar_text)
            .setTextColor(Color.WHITE)
        (sb.view as Snackbar.SnackbarLayout).setBackgroundColor(resources.getColor(R.color.alazar_red))
        sb.show()
    }
}