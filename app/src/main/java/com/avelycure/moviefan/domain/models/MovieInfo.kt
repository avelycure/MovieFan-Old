package com.avelycure.moviefan.domain.models

import com.avelycure.moviefan.common.Constants
import com.avelycure.moviefan.data.local.entities.EntityMovieInfo
import com.avelycure.moviefan.data.remote.dto.details.*

/**
 * Class which represents detailed information of the movie
 */
data class MovieInfo(
    val adult: Boolean = false,
    val budget: Int = 0,
    val imdbId: String? = "",
    val originalLanguage: String = "",
    val originalTitle: String = "",
    val overview: String = "",
    val popularity: Float = 0F,
    val genres: List<MovieGenre> = emptyList(),
    val productionCompanies: List<ProductionCompanies> = emptyList(),
    val productionCountries: List<ProductionCountries> = emptyList(),
    val releaseDate: String = "",
    val spokenLanguages: List<SpokenLanguages> = emptyList(),
    val status: String = "",
    val revenue: Int = 0,
    val tagline: String? = "",
    val title: String = "",
    val voteAverage: Float = 0F,
    val voteCount: Int = 0,
    val posterPath: String? = "",
    val cast: List<String> = emptyList(),
    val movieId: Int = 0
)

fun MovieInfo.getCompanies(): String {
    val companies = buildString {
        for (element in productionCompanies) {
            append(element.name + ", ")
        }
    }
    return if (companies.isNotBlank() && companies.isNotEmpty())
        return companies.substring(0, companies.length - 2)
    else
        ""
}

fun MovieInfo.getCountries(): String {
    val countries = buildString {
        for (element in productionCountries)
            append(element.name + ", ")
    }
    return if (countries.isNotBlank() && countries.isNotEmpty())
        countries.substring(0, countries.length - 2)
    else
        ""
}

fun MovieInfo.getGenres(): String {
    val genres = buildString {
        for (element in genres)
            append(Constants.movieGenre[element.id] + ", ")
    }
    return if (genres.isNotBlank() && genres.isNotEmpty())
        return genres.substring(0, genres.length - 2)
    else
        ""
}

fun MovieInfo.getCast(): String {
    val cast = buildString {
        for (element in cast.take(5))
            append("$element, ")
    }
    return if (cast.isNotBlank() && cast.isNotEmpty())
        return cast.substring(0, cast.length - 2)
    else
        ""
}
