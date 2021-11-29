package com.avelycure.moviefan.data.remote.mappers

import com.avelycure.moviefan.data.local.entities.EntityMovie
import com.avelycure.moviefan.data.remote.dto.movie.MovieListResult

fun MovieListResult.toEntityPopularMovie(): EntityMovie {
    return EntityMovie(
        id = 0,
        title = title,
        originalTitle = original_title,
        posterPath = poster_path,
        genreIds = genre_ids,
        popularity = popularity,
        voteAverage = vote_average,
        releaseDate = release_date,
        movieId = id,
        voteCount = vote_count
    )
}