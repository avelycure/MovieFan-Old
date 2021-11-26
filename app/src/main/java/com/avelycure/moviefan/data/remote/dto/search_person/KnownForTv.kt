package com.avelycure.moviefan.data.remote.dto.search_person

import kotlinx.serialization.Serializable

@Serializable
class KnownForTv(
    override val poster_path: String? = "",
    override val popularity: Float = 0f,
    override val id: Int = 0,
    override val overview: String = "",
    override val backdrop_path: String? = "",
    override val vote_average: Float = 0f,
    override val media_type: String = "",
    val first_air_date: String = "",
    val origin_country: List<String> = emptyList(),
    override val genre_ids: List<Int> = emptyList(),
    override val original_language: String = "",
    override val vote_count: Int = 0,
    val name: String = "",
    val original_name: String = "",
) : KnownFor()