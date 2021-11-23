package com.avelycure.moviefan.domain.interactors

import androidx.paging.PagingData
import com.avelycure.moviefan.data.local.entities.EntityPopularMovie
import com.avelycure.moviefan.data.repository.MovieRepository
import com.avelycure.moviefan.domain.models.Movie
import kotlinx.coroutines.flow.Flow

class GetPopularMovies(
    private val repository: MovieRepository
) {
    /*fun execute(): Flow<PagingData<Movie>> = repository
        .getPopularPager()
        .flow*/

    fun execute(): Flow<PagingData<EntityPopularMovie>> = repository
        .getPagerWithRemoteMediator()
        .flow
}