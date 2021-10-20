package com.avelycure.moviefan.domain

import com.avelycure.moviefan.data.remote.model.MovieListResult

data class PopularMovieCard(
    val posterPath: String?,
    val overview: String,
    val genreIds: List<Int>,
    val title: String,
    val backdropPath: String?,
    val popularity: Float,
)
