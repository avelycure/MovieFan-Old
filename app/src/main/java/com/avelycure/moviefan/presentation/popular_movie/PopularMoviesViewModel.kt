package com.avelycure.moviefan.presentation.popular_movie

import androidx.lifecycle.ViewModel
import com.avelycure.moviefan.data.remote.MovieRepository
import com.avelycure.moviefan.data.remote.dto.popular.MovieListResult
import com.avelycure.moviefan.domain.interactors.GetPopularMovies
import com.avelycure.moviefan.domain.interactors.SearchMovie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class PopularMoviesViewModel
@Inject constructor(
    val getPopularMovies: GetPopularMovies,
    val searchMovie: SearchMovie
) : ViewModel() {
    fun getPopularMovies() = getPopularMovies
        .execute()

    fun searchMovie(query: String) = searchMovie
        .execute(query)
}