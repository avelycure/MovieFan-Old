package com.avelycure.moviefan.data.remote.dto

data class ResponsePolularMovies(
    val page: Int,
    val results: List<MovieListResult>,
    val totalResults: Int,
    val totalPages: Int
    )
