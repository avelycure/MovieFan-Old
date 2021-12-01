package com.avelycure.moviefan.domain.interactors.home

import androidx.paging.map
import com.avelycure.moviefan.data.local.mappers.toMovie
import com.avelycure.moviefan.data.repository.MovieRepository
import kotlinx.coroutines.flow.map

class GetPopularMovies(
    private val repository: MovieRepository
) {
    fun execute() = repository
        .getPagerWithRemoteMediator()
        .flow
        .map { pagingData ->
            pagingData.map { entityPopularMovie ->
                entityPopularMovie.toMovie()
            }
        }
}