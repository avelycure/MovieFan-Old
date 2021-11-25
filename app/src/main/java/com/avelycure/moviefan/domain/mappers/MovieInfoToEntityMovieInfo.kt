package com.avelycure.moviefan.domain.mappers

import com.avelycure.moviefan.data.local.entities.EntityMovieInfo
import com.avelycure.moviefan.domain.models.MovieInfo

fun MovieInfo.toEntityMovieInfo(): EntityMovieInfo {
    return EntityMovieInfo(
        id = 0,
        adult = adult,
        budget = budget,
        imdbId = imdbId,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        overview = overview,
        popularity = popularity,
        genres = genres,
        productionCompanies = productionCompanies,
        productionCountries = productionCountries,
        releaseDate = releaseDate,
        spokenLanguages = spokenLanguages,
        status = status,
        revenue = revenue,
        tagline = tagline,
        voteAverage = voteAverage,
        title = title,
        voteCount = voteCount,
        posterPath = posterPath,
        cast = cast,
        movieId = movieId,
        imagesPosters = imagesPosters,
        imagesBackdrop = imagesBackdrop
    )
}