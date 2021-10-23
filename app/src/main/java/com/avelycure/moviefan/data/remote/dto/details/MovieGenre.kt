package com.avelycure.moviefan.data.remote.dto.details

import kotlinx.serialization.Serializable

@Serializable
data class MovieGenre(
    val id: Int,
    val name: String
)
