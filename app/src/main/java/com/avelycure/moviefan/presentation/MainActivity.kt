package com.avelycure.moviefan.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import com.avelycure.moviefan.R
import com.avelycure.moviefan.common.Constants
import com.avelycure.moviefan.presentation.popular_movie.PopularMoviesFragment
import com.avelycure.moviefan.presentation.search_film.SearchFilmFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //showPopularMovies()
        showSearchMovie()
    }

    private fun showPopularMovies() {
        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragment_container, PopularMoviesFragment())
            .commit()
    }

    private fun showSearchMovie() {
        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragment_container, SearchFilmFragment())
            .commit()
    }
}