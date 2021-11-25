package com.avelycure.moviefan.domain.models

/**
 * Class which represents item in recycler view
 */
data class Movie(
    val title: String,
    val originalTitle: String,
    val posterPath: String?,
    val genreIds: List<Int>,
    val popularity: Float,
    val voteAverage: Float,
    val releaseDate: String,
    val movieId: Int,
    val voteCount: Int
)