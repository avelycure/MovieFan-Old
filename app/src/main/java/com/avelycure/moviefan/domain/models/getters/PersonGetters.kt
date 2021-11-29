package com.avelycure.moviefan.domain.models.getters

import com.avelycure.moviefan.domain.models.Person

fun Person.getMovies(): String {
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

fun Person.getTvs(): String {
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

fun Person.getAlsoKnownAs(): String {
    val sAlsoKnownAs = buildString {
        for (element in alsoKnownAs) {
            append(element + ", ")
        }
    }
    return if (sAlsoKnownAs.isNotBlank() && sAlsoKnownAs.isNotEmpty())
        return sAlsoKnownAs.substring(0, sAlsoKnownAs.length - 2)
    else
        ""
}
