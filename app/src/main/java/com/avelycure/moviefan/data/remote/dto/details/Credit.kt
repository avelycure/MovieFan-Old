package com.avelycure.moviefan.data.remote.dto.details

import kotlinx.serialization.Serializable

@Serializable
data class Credit(
    val cast: List<Cast>,
    val crew: List<Crew>
)