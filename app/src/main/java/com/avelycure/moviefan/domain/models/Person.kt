package com.avelycure.moviefan.domain.models

import com.avelycure.moviefan.data.remote.dto.person.Profile
import com.avelycure.moviefan.data.remote.dto.search_person.KnownForMovie
import com.avelycure.moviefan.data.remote.dto.search_person.KnownForTv

data class Person(
    val id: Int,
    val profilePath: String?,
    val adult: Boolean,
    val name: String,
    val popularity: Float,
    val knownForDepartment: String,
    val knownForMovie: List<String>,
    val knownForTv: List<String>
) {
    var expanded: Boolean = false
    var birthday: String? = ""
    var deathDay: String? = ""
    var alsoKnownAs: List<String> = emptyList()
    var gender: Int = -1
    var biography: String = ""
    var placeOfBirth: String? = ""
    var imdbId: String = ""
    var homepage: String? = ""
    var profileImages: List<Profile> = emptyList()
}