package com.avelycure.moviefan.domain.models

import com.avelycure.moviefan.data.remote.dto.person.Profile

data class PersonInfo(
    val birthday: String? = "",
    val knownForDepartment: String = "",
    val deathDay: String? = "",
    val id: Int = 0,
    val name: String = "",
    val alsoKnownAs: List<String> = emptyList(),
    val gender: Int = -1,
    val biography: String = "",
    val popularity: Float = 0f,
    val placeOfBirth: String? = "",
    val profilePath: String? = "",
    val adult: Boolean = true,
    val imdbId: String = "",
    val homepage: String? = "",
    val profileImages: List<Profile> = emptyList()
)