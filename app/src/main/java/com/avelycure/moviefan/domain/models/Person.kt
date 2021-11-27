package com.avelycure.moviefan.domain.models

import com.avelycure.moviefan.data.remote.dto.search_person.KnownForMovie
import com.avelycure.moviefan.data.remote.dto.search_person.KnownForTv

data class Person(
    var expanded: Boolean = false,
    val id: Int,
    val profilePath: String?,
    val adult: Boolean,
    val name: String,
    val popularity: Float,
    val knownForMovie: List<KnownForMovie>,
    val knownForTv: List<KnownForTv>
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

fun Person.getMovies(): String{
    val movies = buildString {
        for (element in knownForMovie) {
            append(element.title + ", ")
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
            append(element.name + ", ")
        }
    }
    return if (tvs.isNotBlank() && tvs.isNotEmpty())
        return tvs.substring(0, tvs.length - 2)
    else
        ""
}
