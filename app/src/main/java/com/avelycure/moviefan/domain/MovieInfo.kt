package com.avelycure.moviefan.domain

import com.avelycure.moviefan.common.Constants
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
    val revenue: Int,
    val tagline: String?,
    val title: String,
    val voteAverage: Float,
    val voteCount: Int
)

fun MovieInfo.getCompanies(): String {
    val companies = buildString {
        append("Companies: ")
        for (element in productionCompanies) {
            append(element.name + ", ")
        }
    }
    return companies.substring(0, companies.length - 2)
}

fun MovieInfo.getCountries(): String {
    val countries = buildString {
        append("Countries: ")
        for (element in productionCountries)
            append(element.name + ", ")
    }
    return countries.substring(0, countries.length - 2)
}

fun MovieInfo.getGenres(): String {
    val genres = buildString {
        append("Genres: ")
        for (element in genres)
            append(Constants.movieGenre[element.id] + ", ")
    }
    return genres.substring(0, genres.length - 2)
}
