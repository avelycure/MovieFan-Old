package com.avelycure.moviefan.presentation.home

import android.os.Parcelable
import androidx.lifecycle.ViewModel
import com.avelycure.moviefan.domain.interactors.GetPopularMovies
import com.avelycure.moviefan.domain.interactors.SearchMovie
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
@Inject constructor(
    private val getPopularMovies: GetPopularMovies,
    private val searchMovie: SearchMovie
) : ViewModel() {
    var state: Parcelable? = null

    fun getPopularMovies() = getPopularMovies
        .execute()

    fun searchMovie(query: String) = searchMovie
        .execute(query)
}