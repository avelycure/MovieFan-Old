package com.avelycure.moviefan.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.avelycure.moviefan.R
import com.avelycure.moviefan.data.remote.PopularMovieAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    private val mainActivityViewModel: MainActivityViewModel by viewModels()
    private lateinit var adapter: PopularMovieAdapter
    private lateinit var rvPopularMovie: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navController = = Navigation.findNavController(this, R.id.auth_nav_host_fragment)

        rvPopularMovie = findViewById(R.id.rv_popular_movies)
        adapter = PopularMovieAdapter()
        rvPopularMovie.layoutManager = LinearLayoutManager(this)
        rvPopularMovie.adapter = adapter

        fetchPopularMovies()
    }

    private fun fetchPopularMovies() {
        lifecycleScope.launch {
            mainActivityViewModel
                .getPhotos()
                .distinctUntilChanged()
                .collectLatest {
                    adapter.submitData(it)
                }
        }
    }
}