package com.avelycure.moviefan.data.remote.mappers

import com.avelycure.moviefan.data.remote.dto.search_person.ResultPerson
import com.avelycure.moviefan.domain.models.Person

fun ResultPerson.toPerson(): Person {
    return Person(
        profilePath = profile_path,
        adult = adult,
        id = id,
        knownFor = known_for,
        name = name,
        popularity = popularity
    )
}