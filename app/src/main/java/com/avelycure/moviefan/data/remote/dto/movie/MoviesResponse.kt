package com.avelycure.moviefan.data.remote.dto.movie

import kotlinx.serialization.Serializable

@Serializable
data class MoviesResponse(
    val page: Int = 0,
    val results: List<MovieListResult> = emptyList(),
    val total_results: Int = 0,
    val total_pages: Int = 0
)

