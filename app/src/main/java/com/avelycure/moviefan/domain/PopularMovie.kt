package com.avelycure.moviefan.domain

data class PopularMovie(
    val posterPath: String?,
    val overview: String,
    val genreIds: List<Int>,
    val title: String,
    val popularity: Float,
    val voteAverage: Float
)
