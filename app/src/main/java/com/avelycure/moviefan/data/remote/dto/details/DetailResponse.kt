package com.avelycure.moviefan.data.remote.dto.details

import kotlinx.serialization.Serializable

@Serializable
data class DetailResponse(
    val adult: Boolean,
    val backdrop_path: String?,
    val belongs_to_collection: DetailResponse?,
    val budget: Int,
    val homepage: String?,
    val id: Int,
    val imdb_id: String?,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Float,
    val poster_path: String?,
    val genres: List<MovieGenre>,
    val production_companies: List<ProductionCompanies>,
    val production_countries: List<ProductionCountries>,
    val status: String,
    val tagline: String?,
    val title: String,
    val video: Boolean,
    val vote_average: Float,
    val vote_count: Int
)
