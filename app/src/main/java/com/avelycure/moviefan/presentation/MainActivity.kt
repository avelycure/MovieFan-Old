package com.avelycure.moviefan.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
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

        this.navController = Navigation.findNavController(this, R.id.fragment_container)
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