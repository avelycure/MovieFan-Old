package com.avelycure.moviefan.data.remote.mappers

import com.avelycure.moviefan.data.remote.dto.search_person.ResultPerson
import com.avelycure.moviefan.domain.models.Person

fun ResultPerson.toPerson(): Person {
    return Person(
        profile_path = profile_path,
        adult = adult,
        id = id,
        known_for = known_for,
        name = name,
        popularity = popularity
    )
}