package com.avelycure.moviefan.domain.models

import com.avelycure.moviefan.data.remote.dto.search_person.KnownFor

data class Person(
    var expanded: Boolean = false,
    val id: Int,
    val profilePath: String?,
    val adult: Boolean,
    val name: String,
    val popularity: Float,
    var knownFor: List<KnownFor>
) {
    var birthday: String? = ""
    var knownForDepartment: String = ""
    var deathDay: String? = ""
    var alsoKnownAs: List<String> = emptyList()
    var gender: Int = -1
    var biography: String = ""
    var placeOfBirth: String? = ""
    var imdbId: String = ""
    var homepage: String? = ""
}
