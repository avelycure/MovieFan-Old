package com.avelycure.moviefan.data.remote.dto.search_person

import androidx.core.os.persistableBundleOf
import com.avelycure.moviefan.data.local.entities.EntityPopularPerson
import kotlinx.serialization.Serializable

@Serializable
data class ResultPerson(
    val adult: Boolean,
    val gender: Int,
    val id: Int,
    val known_for: List<KnownFor>,
    val known_for_department: String?,
    val name: String,
    val popularity: Float,
    val profile_path: String?,
)

fun ResultPerson.toEntityPopularPerson(): EntityPopularPerson {
    val knownForMovie = mutableListOf<String>()
    val knownForTv = mutableListOf<String>()

    for (media in known_for) {
        if (media is KnownForMovie)
            knownForMovie.add(media.title)
        if (media is KnownForTv)
            knownForTv.add(media.name)
    }

    return EntityPopularPerson(
        id = 0,
        adult = adult,
        gender = gender,
        personId = id,
        knownForDepartment = known_for_department,
        name = name,
        popularity = popularity,
        profilePath = profile_path,
        knownForMovie = knownForMovie,
        knownForTv = knownForTv
    )
}