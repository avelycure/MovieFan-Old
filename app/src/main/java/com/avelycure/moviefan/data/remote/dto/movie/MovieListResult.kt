package com.avelycure.moviefan.data.remote.dto.movie

import com.avelycure.moviefan.data.local.entities.EntityPopularMovie
import kotlinx.serialization.Serializable

@Serializable
data class MovieListResult(
    val poster_path: String?,
    val adult: Boolean,
    val overview: String,
    val release_date: String = "",
    val genre_ids: List<Int>,
    val id: Int,
    val original_title: String,
    val original_language: String,
    val title: String,
    val backdrop_path: String?,
    val popularity: Float,
    val vote_count: Int,
    val video: Boolean,
    val vote_average: Float
)

fun MovieListResult.toEntityPopularMovie(): EntityPopularMovie{
    return EntityPopularMovie(
        id = 0,
        dateOfSave = 0,
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

