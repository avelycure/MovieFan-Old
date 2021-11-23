package com.avelycure.moviefan.domain.interactors

import com.avelycure.moviefan.data.repository.MovieRepository
import com.avelycure.moviefan.domain.models.MovieInfo

class SaveToCache(
    private val repository: MovieRepository
) {
    suspend fun execute(movieInfo: MovieInfo) {
        repository.saveMovieInfo(movieInfo)
    }
}