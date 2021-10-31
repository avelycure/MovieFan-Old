package com.avelycure.moviefan.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.avelycure.moviefan.R
import com.avelycure.moviefan.presentation.popular_movie.PopularMoviesFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showPopularMovies()
    }

    private fun showPopularMovies() {
        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragment_container, PopularMoviesFragment())
            .commit()
    }
}