package com.avelycure.moviefan.domain.models

import com.avelycure.moviefan.data.remote.dto.person.Profile
import com.avelycure.moviefan.data.remote.dto.search_person.KnownForMovie
import com.avelycure.moviefan.data.remote.dto.search_person.KnownForTv

data class Person(
    var expanded: Boolean = false,
    val id: Int,
    val profilePath: String?,
    val adult: Boolean,
    val name: String,
    val popularity: Float,
    val knownForDepartment: String,
    val knownForMovie: List<String>,
    val knownForTv: List<String>
) {
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

fun Person.getMovies(): String{
    val movies = buildString {
        for (element in knownForMovie) {
            append(element + ", ")
        }
    }
    return if (movies.isNotBlank() && movies.isNotEmpty())
        return movies.substring(0, movies.length - 2)
    else
        ""
}

fun Person.getTvs(): String{
    val tvs = buildString {
        for (element in knownForTv) {
            append(element + ", ")
        }
    }
    return if (tvs.isNotBlank() && tvs.isNotEmpty())
        return tvs.substring(0, tvs.length - 2)
    else
        ""
}
