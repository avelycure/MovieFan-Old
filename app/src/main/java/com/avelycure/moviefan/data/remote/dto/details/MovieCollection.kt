package com.avelycure.moviefan.data.remote.dto.details

import kotlinx.serialization.Serializable

@Serializable
data class MovieCollection(
    val id: Int,
    val name: String,
    val poster_path: String?,
    val backdrop_path: String?
)
