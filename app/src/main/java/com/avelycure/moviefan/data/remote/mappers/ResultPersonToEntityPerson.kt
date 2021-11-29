package com.avelycure.moviefan.data.remote.mappers

import com.avelycure.moviefan.data.local.entities.EntityPerson
import com.avelycure.moviefan.data.remote.dto.search_person.KnownForMovie
import com.avelycure.moviefan.data.remote.dto.search_person.KnownForTv
import com.avelycure.moviefan.data.remote.dto.search_person.ResultPerson

fun ResultPerson.toEntityPopularPerson(): EntityPerson {
    val knownForMovie = mutableListOf<String>()
    val knownForTv = mutableListOf<String>()

    for (media in known_for) {
        if (media is KnownForMovie)
            knownForMovie.add(media.title)
        if (media is KnownForTv)
            knownForTv.add(media.name)
    }

    return EntityPerson(
        id = 0,
        adult = adult,
        gender = gender,
        personId = id,
        knownForDepartment = known_for_department ?: "",
        name = name,
        popularity = popularity,
        profilePath = profile_path,
        knownForMovie = knownForMovie,
        knownForTv = knownForTv
    )
}