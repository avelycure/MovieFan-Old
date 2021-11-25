package com.avelycure.moviefan.data.remote.dto.details

import kotlinx.serialization.Serializable

@Serializable
data class MovieImages(
    //val id: Int = 0,
    val backdrops: List<Backdrop> = emptyList(),
    val logos: List<Logo>,
    val posters: List<Poster> = emptyList(),
)

@Serializable
data class Logo(
    val aspect_ratio: Float,
    val height: Int,
    val iso_639_1: String?,
    val file_path: String,
    val vote_average: Float,
    val vote_count:Int,
    val width: Int
)