package com.avelycure.moviefan.presentation

import android.graphics.Color
import android.net.ConnectivityManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import com.avelycure.moviefan.R
import com.avelycure.moviefan.presentation.popular_movie.PopularMoviesFragment
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var btnRetry: AppCompatButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnRetry = findViewById(R.id.main_btn_restart)
        btnRetry.setOnClickListener {
            if (isOnline()){
                btnRetry.visibility = View.INVISIBLE
                showPopularMovies()
            }
            else
                showNoInternetConnectionError()
        }

        if (isOnline())
            showPopularMovies()
        else {
            btnRetry.visibility = View.VISIBLE
            showNoInternetConnectionError()
        }
    }

    private fun showNoInternetConnectionError() {
        val sb = Snackbar.make(
            this,
            btnRetry,
            "No internet connection",
            Snackbar.LENGTH_SHORT
        )
        (sb.view as Snackbar.SnackbarLayout).findViewById<TextView>(R.id.snackbar_text)
            .setTextColor(
                Color.WHITE
            )
        (sb.view as Snackbar.SnackbarLayout).setBackgroundColor(resources.getColor(R.color.light_blue))
        sb.show()
    }

    private fun showPopularMovies() {
        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragment_container, PopularMoviesFragment())
            .commit()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == android.R.id.home) {
            supportFragmentManager.popBackStack()
            true
        } else {
            super.onOptionsItemSelected(item)
        }
    }

    private fun isOnline(): Boolean = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        (getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager).activeNetwork != null
    } else {
        true
    }
}