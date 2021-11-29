package com.avelycure.moviefan.data.local.mappers

import com.avelycure.moviefan.data.local.entities.EntityPerson
import com.avelycure.moviefan.domain.models.Person

fun EntityPerson.toPerson(): Person {
    return Person(
        id = personId,
        profilePath = profilePath,
        adult = adult,
        name = name,
        popularity = popularity,
        knownForMovie = knownForMovie,
        knownForTv = knownForTv,
        knownForDepartment = knownForDepartment
    )
}