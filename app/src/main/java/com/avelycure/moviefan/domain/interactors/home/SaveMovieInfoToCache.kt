package com.avelycure.moviefan.domain.interactors.home

import com.avelycure.moviefan.data.repository.MovieRepository
import com.avelycure.moviefan.domain.models.MovieInfo

class SaveMovieInfoToCache(
    private val repository: MovieRepository
) {
    suspend fun execute(movieInfo: MovieInfo) {
        repository.saveMovieInfo(movieInfo)
    }
}