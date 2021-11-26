package com.avelycure.moviefan.data.remote.dto.search_person

import kotlinx.serialization.Serializable

@Serializable
data class ResultPerson(
    val profile_path: String?,
    val adult: Boolean,
    val id: Int,
    val known_for: List<KnownFor>,
    val name: String,
    val popularity: Float
)