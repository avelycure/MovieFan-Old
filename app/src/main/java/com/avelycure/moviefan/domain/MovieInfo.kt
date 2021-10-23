package com.avelycure.moviefan.domain

import com.avelycure.moviefan.data.remote.dto.details.*

data class MovieInfo(
    val adult: Boolean,
    val budget: Int,
    val imdbId: String?,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Float,
    val genres: List<MovieGenre>,
    val productionCompanies: List<ProductionCompanies>,
    val productionCountries: List<ProductionCountries>,
    val releaseDate: String,
    val spokenLanguages: List<SpokenLanguages>,
    val status: String,
    val tagline: String?,
    val title: String,
    val voteAverage: Float,
    val voteCount: Int
)
