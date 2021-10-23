package com.avelycure.moviefan.domain

import com.avelycure.moviefan.data.remote.dto.details.*

data class MovieInfo(
    val adult: Boolean,
    val budget: Int,
    val imdb_id: String?,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Float,
    val genres: List<MovieGenre>,
    val production_companies: List<ProductionCompanies>,
    val production_countries: List<ProductionCountries>,
    val spoken_languages: List<SpokenLanguages>,
    val status: String,
    val tagline: String?,
    val title: String,
    val vote_average: Float
)
