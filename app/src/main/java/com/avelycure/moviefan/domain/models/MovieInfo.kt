package com.avelycure.moviefan.domain.models

import com.avelycure.moviefan.common.TemporaryConstants
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
    val movieId: Int = 0,
    val imagesBackdrop: List<String> = emptyList(),
    val imagesPosters: List<String> = emptyList(),
    val similar: List<Movie> = emptyList()
)


