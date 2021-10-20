package com.avelycure.moviefan.domain

data class PopularMovieCard(
    val posterPath: String?,
    val overview: String,
    val genreIds: List<Int>,
    val title: String,
    val backdropPath: String?,
    val popularity: Float,
)
