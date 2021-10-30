package com.avelycure.moviefan.presentation.popular_movie

import android.content.Context
import android.graphics.Color
import android.net.ConnectivityManager
import android.os.Build
import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.avelycure.moviefan.R
import com.avelycure.moviefan.common.Constants
import com.avelycure.moviefan.data.remote.PopularMovieAdapter
import com.avelycure.moviefan.di.modules.PopularMovieAdapterFactory
import com.avelycure.moviefan.presentation.AppInfo
import com.avelycure.moviefan.presentation.movie_info.MovieInfoFragment
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class PopularMoviesFragment : Fragment() {
    @Inject
    lateinit var movieAdapterFactory: PopularMovieAdapterFactory
    lateinit var movieAdapter: PopularMovieAdapter
    private val popularMoviesViewModel: PopularMoviesViewModel by viewModels()

    private lateinit var rvPopularMovie: RecyclerView
    private lateinit var loadingProgressBar: ProgressBar
    private lateinit var btnRetry: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragement_popular_movies, container, false)
        (activity as AppCompatActivity).setSupportActionBar(view.findViewById(R.id.pm_toolbar))
        (activity as AppCompatActivity).supportActionBar?.title = Constants.POPULAR_MOVIE_TITLE_DEFAULT

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

        rvPopularMovie.layoutManager = LinearLayoutManager(view.context)

        setHasOptionsMenu(true)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter(btnRetry)
        fetchPopularMovies()
    }

    private fun fetchPopularMovies() {
        lifecycleScope.launch {
            popularMoviesViewModel
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

    private fun initAdapter(view: View) {
        movieAdapter = movieAdapterFactory.create {
            if (isOnline()) {
                val fragmentInfo = MovieInfoFragment()
                fragmentInfo.arguments = bundleOf(
                    Constants.ID_KEY to it.movieId,
                    Constants.MOVIE_TITLE to it.title
                )
                activity?.supportFragmentManager
                    ?.beginTransaction()
                    ?.addToBackStack(Constants.POPULAR_MOVIE_TAG)
                    ?.add(R.id.fragment_container, fragmentInfo)
                    ?.commit()
            } else
                showNoInternetConnectionError(view)
        }

        movieAdapter.addLoadStateListener { loadState ->
            if (loadState.refresh is LoadState.Loading) {
                loadingProgressBar.visibility = View.VISIBLE
            } else {
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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        if((activity as AppCompatActivity).supportActionBar?.title == Constants.POPULAR_MOVIE_TITLE_DEFAULT)
            inflater.inflate(R.menu.toolbar_menu, menu)
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

}