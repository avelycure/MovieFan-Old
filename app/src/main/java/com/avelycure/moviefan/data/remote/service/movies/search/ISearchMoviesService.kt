package com.avelycure.moviefan.data.remote.service.movies.search

import com.avelycure.moviefan.data.remote.dto.movie.MoviesResponse

interface ISearchMoviesService {
    suspend fun getMoviesByName(query: String, page: Int): MoviesResponse
}