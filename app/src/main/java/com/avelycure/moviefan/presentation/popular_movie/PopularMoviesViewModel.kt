package com.avelycure.moviefan.presentation.popular_movie

import androidx.lifecycle.ViewModel
import com.avelycure.moviefan.domain.interactors.GetPopularMovies
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PopularMoviesViewModel
@Inject constructor(
    val getPopularMovies: GetPopularMovies
) : ViewModel() {
    fun getPopularMovies() = getPopularMovies
        .execute()
}