package com.avelycure.moviefan.presentation.home

import android.app.SearchManager
import android.content.Context
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
import androidx.paging.map
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.avelycure.moviefan.R
import com.avelycure.moviefan.common.Constants
import com.avelycure.moviefan.data.remote.adapters.MovieAdapter
import com.avelycure.moviefan.domain.models.Movie
import com.avelycure.moviefan.presentation.app_info.AppInfo
import com.avelycure.moviefan.presentation.movie_info.MovieInfoFragment
import com.avelycure.moviefan.utils.getQueryChangeStateFlow
import com.avelycure.moviefan.utils.isOnline
import com.avelycure.moviefan.utils.showError
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Fragment to represent popular movies and movies that were found while searching
 */
@AndroidEntryPoint
class HomeFragment : Fragment() {
    // Injecting adapter for faster reaction on screen rotation
    @Inject
    lateinit var movieAdapter: MovieAdapter

    private val homeViewModel: HomeViewModel by viewModels()

    private lateinit var searchView: SearchView
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

    /**
     * Creating menu and make searchView a stateFlow, so when typing we are getting movies that
     * matches the string we have already typed
     */
    @FlowPreview
    @ExperimentalCoroutinesApi
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        if ((activity as AppCompatActivity).supportActionBar?.title == Constants.POPULAR_MOVIE_TITLE_DEFAULT) {
            menu.clear()
            inflater.inflate(R.menu.toolbar_menu, menu)
            initSearchView(menu)

            /*lifecycleScope.launch {
                searchView.getQueryChangeStateFlow()
                    .debounce(500)
                    .filter { query ->
                        return@filter query.isNotEmpty()
                    }
                    .distinctUntilChanged()
                    .flatMapLatest { query ->
                        homeViewModel.searchMovie(query)
                    }
                    .flowOn(Dispatchers.IO)
                    .collectLatest {
                        movieAdapter.submitData(it)
                    }
            }*/
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

        movieAdapter.onClickedItem = { movie ->
            //if (isOnline(activity as AppCompatActivity))
                openMovieInfoFragment(movie)
            /*else
                showError(
                    view,
                    requireContext(),
                    Constants.NO_INTERNET_CONNECTION
                )*/
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
                    showError(
                        btnRetry,
                        requireContext(),
                        Constants.NO_INTERNET_CONNECTION
                    )
                }
            }
        }
        rvPopularMovie.adapter = movieAdapter.withLoadStateFooter(
            footer = MovieLoadStateAdapter { movieAdapter.retry() }
        )
    }

    // To restore rv state after rotation
    override fun onResume() {
        super.onResume()
        if (homeViewModel.state != null)
            rvPopularMovie.layoutManager?.onRestoreInstanceState(homeViewModel.state)
    }

    // To restore rv state after rotation
    override fun onPause() {
        super.onPause()
        homeViewModel.state = rvPopularMovie.layoutManager?.onSaveInstanceState()
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
            if (isOnline(activity as AppCompatActivity)) {
                btnRetry.visibility = View.INVISIBLE
                fetchPopularMovies()
            } else
                showError(
                    view,
                    requireContext(),
                    Constants.NO_INTERNET_CONNECTION
                )
        }
    }

    private fun initSearchView(menu: Menu) {
        val searchManager =
            (activity as AppCompatActivity).getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView = menu.findItem(R.id.search_view).actionView as SearchView
        searchView.setSearchableInfo(searchManager.getSearchableInfo((activity as AppCompatActivity).componentName))
        searchView.setIconifiedByDefault(false)

        // The default state of the homeFragment is showing popular movies, so when we close
        // searchView we are calling fetchPopularMovies()
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

    private fun openMovieInfoFragment(movie: Movie) {
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
}