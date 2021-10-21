package com.avelycure.moviefan.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.avelycure.moviefan.R
import com.avelycure.moviefan.data.remote.PopularMovieAdapter
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PopularMoviesFragment : Fragment() {
    private lateinit var movieAdapter: PopularMovieAdapter
    private lateinit var rvPopularMovie: RecyclerView
    private val popularMoviesViewModel: PopularMoviesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragement_popular_movies, container, false)

        rvPopularMovie = view.findViewById(R.id.rv_popular_movies)
        rvPopularMovie.layoutManager = LinearLayoutManager(view.context)
        movieAdapter = PopularMovieAdapter()

        val loadStateAdapter = movieAdapter.withLoadStateFooter(
            footer = MovieLoadStateAdapter { movieAdapter.retry() }
        )

        rvPopularMovie.adapter = loadStateAdapter


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
}