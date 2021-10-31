package com.avelycure.moviefan.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.avelycure.moviefan.R
import com.avelycure.moviefan.presentation.popular_movie.PopularMoviesFragment
import com.avelycure.moviefan.presentation.search_film.SearchMovieFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigationView = findViewById(R.id.bottom_navigation_view)
        //todo add reselection
        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.popular_movies -> {
                    showPopularMovies()
                    true
                }
                R.id.search_movie -> {
                    showSearchMovie()
                    true
                }
                else -> false
            }
        }

        bottomNavigationView.setOnItemReselectedListener { item ->
            when (item.itemId) {
                R.id.popular_movies -> {
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.fragment_container, PopularMoviesFragment())
                        .commit()
                    true
                }
                R.id.search_movie -> {
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.fragment_container, SearchMovieFragment())
                        .commit()
                    true
                }
                else -> false
            }
        }

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
            .add(R.id.fragment_container, SearchMovieFragment())
            .commit()
    }
}