package com.avelycure.moviefan.data.remote.dto

import com.avelycure.moviefan.domain.PopularMovie
import kotlinx.serialization.Serializable

@Serializable
data class MovieListResult(
    val poster_path: String?,
    val adult: Boolean,
    val overview: String,
    val release_date: String,
    val genre_ids: List<Int>,
    val id: Int,
    val original_title:String,
    val original_language: String,
    val title: String,
    val backdrop_path: String?,
    val popularity: Float,
    val vote_count: Int,
    val video: Boolean,
    val vote_average: Float
)

fun MovieListResult.toPopularMovie(): PopularMovie{
    return PopularMovie(
        posterPath = poster_path,
        overview = overview,
        genreIds = genre_ids,
        title = title,
        backdropPath = backdrop_path,
        popularity = popularity
    )
}
