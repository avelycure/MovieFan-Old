package com.avelycure.moviefan.data.remote.dto.details

import kotlinx.serialization.Serializable

@Serializable
data class Backdrop(
    val aspect_ratio: Float,
    val file_path: String,
    val height: Int,
    val iso_639_1: String?,
    val vote_average: Int,
    val vote_count: Int,
    val width: Int
)
