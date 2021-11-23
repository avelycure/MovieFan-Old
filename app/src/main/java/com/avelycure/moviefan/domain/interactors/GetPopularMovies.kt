package com.avelycure.moviefan.domain.interactors

import com.avelycure.moviefan.data.repository.MovieRepository

class GetPopularMovies(
    private val repository: MovieRepository
) {
    fun execute() = repository
        .getPagerWithRemoteMediator()
        .flow
}