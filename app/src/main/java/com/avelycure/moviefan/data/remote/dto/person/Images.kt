package com.avelycure.moviefan.data.remote.dto.person

import kotlinx.serialization.Serializable

@Serializable
data class Images(
    val profiles: List<Profile>?
)