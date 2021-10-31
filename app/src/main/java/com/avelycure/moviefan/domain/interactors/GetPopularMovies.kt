package com.avelycure.moviefan.domain.interactors

import androidx.paging.PagingData
import com.avelycure.moviefan.data.remote.repository.MovieRepository
import com.avelycure.moviefan.domain.models.PopularMovie
import kotlinx.coroutines.flow.Flow

class GetPopularMovies(
    private val repository: MovieRepository
) {
    fun execute(): Flow<PagingData<PopularMovie>> = repository
        .getPager()
        .flow
}