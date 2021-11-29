package com.avelycure.moviefan.data.remote.dto.details

import kotlinx.serialization.Serializable

@Serializable
data class MovieImages(
    val backdrops: List<Backdrop> = emptyList(),
    val logos: List<Logo>,
    val posters: List<Poster> = emptyList(),
)