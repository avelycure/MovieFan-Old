package com.avelycure.moviefan.data.remote.dto.person

import kotlinx.serialization.Serializable

@Serializable
data class PersonImages(
    val id: Int,
    val profiles: List<Profile>
)
