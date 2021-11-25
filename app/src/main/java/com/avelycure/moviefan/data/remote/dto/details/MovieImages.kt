package com.avelycure.moviefan.data.remote.dto.details

import kotlinx.serialization.Serializable

@Serializable
data class MovieImages(
    val id: Int,
    val backdrops: List<Backdrop>,
    val posters: List<Poster>
)
