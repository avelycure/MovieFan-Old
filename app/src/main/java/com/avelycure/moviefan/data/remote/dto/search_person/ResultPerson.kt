package com.avelycure.moviefan.data.remote.dto.search_person

import kotlinx.serialization.Serializable

@Serializable
data class ResultPerson(
    val adult: Boolean = true,
    val gender: Int = -1,
    val id: Int = 0,
    val known_for: List<KnownFor> = emptyList(),
    val known_for_department: String? = "",
    val name: String = "",
    val popularity: Float = 0f,
    val profile_path: String? = "",
)

