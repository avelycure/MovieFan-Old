package com.avelycure.moviefan.domain.models

import com.avelycure.moviefan.data.remote.dto.search_person.KnownFor

data class Person(
    val profile_path: String?,
    val adult: Boolean,
    val id: Int,
    val known_for: List<KnownFor>,
    val name: String,
    val popularity: Float
)
