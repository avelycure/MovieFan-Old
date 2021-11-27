package com.avelycure.moviefan.domain.models

data class PersonInfo(
    val birthday: String? = "",
    val knownForDepartment: String = "",
    val deathDay: String?="",
    val id: Int=0,
    val name: String="",
    val alsoKnownAs: List<String> = emptyList(),
    val gender: Int = -1,
    val biography: String = "",
    val popularity: Float = 0f,
    val placeOfBirth: String? = "",
    val profilePath: String? = "",
    val adult: Boolean = true,
    val imdbId: String = "",
    val homepage: String? = ""
)