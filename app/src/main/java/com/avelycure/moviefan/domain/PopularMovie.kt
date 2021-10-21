package com.avelycure.moviefan.domain

data class PopularMovie(
    val title: String,
    val originalTitle: String,
    val posterPath: String?,
    val overview: String,
    val genreIds: List<Int>,
    val popularity: Float,
    val voteAverage: Float,
    val releaseDate: String
)
