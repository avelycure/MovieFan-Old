package com.avelycure.moviefan.data.local.mappers

import com.avelycure.moviefan.data.local.entities.EntityPopularPerson
import com.avelycure.moviefan.data.remote.dto.search_person.KnownForMovie
import com.avelycure.moviefan.data.remote.dto.search_person.KnownForTv
import com.avelycure.moviefan.domain.models.Person

fun EntityPopularPerson.toPerson(): Person {
    return Person(
        id = personId,
        profilePath = profilePath,
        adult = adult,
        name = name,
        popularity = popularity,
        knownForMovie = knownForMovie,
        knownForTv = knownForTv
    )
}