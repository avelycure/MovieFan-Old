package com.avelycure.moviefan.data.remote.dto.popular.mappers

import com.avelycure.moviefan.data.remote.dto.popular.MovieListResult
import com.avelycure.moviefan.domain.models.PopularMovie


fun MovieListResult.toPopularMovie(): PopularMovie {
    return PopularMovie(
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