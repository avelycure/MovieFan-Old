package com.avelycure.moviefan.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class ResponsePolularMovies(
    val page: Int,
    val results: List<MovieListResult>,
    val totalResults: Int,
    val totalPages: Int
    )
