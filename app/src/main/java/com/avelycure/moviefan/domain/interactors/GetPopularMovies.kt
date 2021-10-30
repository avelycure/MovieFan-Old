package com.avelycure.moviefan.domain.interactors

import androidx.paging.PagingData
import com.avelycure.moviefan.data.remote.MovieRepository
import com.avelycure.moviefan.domain.models.PopularMovie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetPopularMovies(
    private val repository: MovieRepository
) {
    fun execute(): Flow<PagingData<PopularMovie>> = repository
        .getPager()
        .flow
}