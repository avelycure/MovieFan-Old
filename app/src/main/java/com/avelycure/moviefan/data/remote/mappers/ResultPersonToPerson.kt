package com.avelycure.moviefan.data.remote.mappers

import com.avelycure.moviefan.data.remote.dto.search_person.KnownForMovie
import com.avelycure.moviefan.data.remote.dto.search_person.KnownForTv
import com.avelycure.moviefan.data.remote.dto.search_person.ResultPerson
import com.avelycure.moviefan.domain.models.Person

fun ResultPerson.toPerson(): Person {
    val knownForMovie = mutableListOf<KnownForMovie>()
    val knownForTv = mutableListOf<KnownForTv>()

    for (media in known_for) {
        if (media is KnownForMovie)
            knownForMovie.add(media)
        if (media is KnownForTv)
            knownForTv.add(media)
    }

    return Person(
        profilePath = profile_path,
        adult = adult,
        id = id,
        knownForMovie = knownForMovie.map { it.title },
        knownForTv = knownForTv.map { it.name },
        name = name,
        popularity = popularity,
        knownForDepartment = known_for_department ?: ""
    )
}