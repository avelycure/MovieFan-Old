package com.avelycure.moviefan.presentation.popular_movie

import android.content.Context
import android.graphics.Color
import android.net.ConnectivityManager
import android.os.Build
import android.os.Bundle
import android.view.*
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
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
    private lateinit var btnRetry: com.google.android.material.button.MaterialButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragement_popular_movies, container, false)
        (activity as AppCompatActivity).setSupportActionBar(view.findViewById(R.id.toolbar))
        (activity as AppCompatActivity).supportActionBar?.title = "Popular movies"
        
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
                .getPhotos()
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
            "No internet connection",
            Snackbar.LENGTH_SHORT
        )
        (sb.view as Snackbar.SnackbarLayout).findViewById<TextView>(R.id.snackbar_text)
            .setTextColor(Color.WHITE)
        (sb.view as Snackbar.SnackbarLayout).setBackgroundColor(resources.getColor(R.color.light_blue))
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
                    ?.addToBackStack("popular_movie")
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
        inflater.inflate(R.menu.toolbar_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == R.id.action_info) {
            activity?.supportFragmentManager
                ?.beginTransaction()
                ?.addToBackStack("popular_movie")
                ?.add(R.id.fragment_container, AppInfo())
                ?.commit()
            true
        } else {
            super.onOptionsItemSelected(item)
        }
    }

}