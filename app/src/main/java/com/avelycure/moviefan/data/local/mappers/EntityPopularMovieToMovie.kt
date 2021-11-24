package com.avelycure.moviefan.data.local.mappers

import com.avelycure.moviefan.data.local.entities.EntityPopularMovie
import com.avelycure.moviefan.domain.models.Movie

fun EntityPopularMovie.toMovie(): Movie {
    return Movie(
        title = title,
        originalTitle = originalTitle,
        posterPath = posterPath,
        genreIds = genreIds,
        popularity = popularity,
        voteAverage = voteAverage,
        releaseDate = releaseDate,
        movieId = movieId,
        voteCount = voteCount
    )
}