package com.avelycure.moviefan.presentation.home

import androidx.lifecycle.ViewModel
import com.avelycure.moviefan.domain.interactors.GetPopularMovies
import com.avelycure.moviefan.domain.interactors.SearchMovie
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
@Inject constructor(
    val getPopularMovies: GetPopularMovies,
    val searchMovie: SearchMovie
) : ViewModel() {
    fun getPopularMovies() = getPopularMovies
        .execute()

    fun searchMovie(query: String) = searchMovie
        .execute(query)
}