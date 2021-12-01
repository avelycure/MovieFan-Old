package com.avelycure.moviefan.data.remote.service.movies.popular

import com.avelycure.moviefan.data.remote.dto.movie.MoviesResponse

interface IPopularMoviesService {
    suspend fun getPopularMovies(nextPage: Int): MoviesResponse

}