package com.avelycure.moviefan.presentation.popular_movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.avelycure.moviefan.data.remote.MovieRepository
import com.avelycure.moviefan.domain.interactors.GetPopularMovies
import com.avelycure.moviefan.domain.models.PopularMovie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class PopularMoviesViewModel
@Inject constructor(
    val getPopularMovies: GetPopularMovies
): ViewModel() {
    fun getPopularMovies() = getPopularMovies.execute()
}