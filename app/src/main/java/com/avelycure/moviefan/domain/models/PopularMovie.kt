package com.avelycure.moviefan.domain.models

data class PopularMovie(
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
