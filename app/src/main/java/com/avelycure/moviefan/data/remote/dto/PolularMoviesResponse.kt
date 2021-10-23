package com.avelycure.moviefan.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class PolularMoviesResponse(
    val page: Int = 0,
    val results: List<MovieListResult> = emptyList(),
    val total_results: Int = 0,
    val total_pages: Int = 0
)
