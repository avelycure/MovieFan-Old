package com.avelycure.moviefan.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.avelycure.moviefan.R
import com.avelycure.moviefan.presentation.home.HomeFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(savedInstanceState == null)
            showPopularMovies()
    }

    private fun showPopularMovies() {
        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragment_container, HomeFragment())
            .commit()
    }

    override fun onPause() {
        super.onPause()
    }
}