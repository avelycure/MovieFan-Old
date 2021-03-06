package com.avelycure.moviefan.data.remote.mappers

import com.avelycure.moviefan.data.remote.dto.movie.MovieListResult
import com.avelycure.moviefan.domain.models.Movie

fun MovieListResult.toMovie(): Movie {
    return Movie(
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
