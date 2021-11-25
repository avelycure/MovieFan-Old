package com.avelycure.moviefan.data.remote.dto.person

import kotlinx.serialization.Serializable

@Serializable
data class ResponsePerson(
    val birthday: String?,
    val known_for_department: String,
    val deathday: String?,
    val id: Int,
    val name: String,
    val also_known_as: List<String>,
    val gender: Int,
    val biography: String,
    val popularity: Float,
    val place_of_birth: String?,
    val profile_path: String?,
    val adult: Boolean,
    val imdb_id: String,
    val homepage: String?
)
