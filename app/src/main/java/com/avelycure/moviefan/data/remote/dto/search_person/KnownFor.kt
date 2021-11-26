package com.avelycure.moviefan.data.remote.dto.search_person

import kotlinx.serialization.Serializable

@Serializable
sealed class KnownFor {
    abstract val poster_path: String?
    abstract val overview: String
    abstract val genre_ids: List<Int>
    abstract val id: Int
    abstract val media_type: String
    abstract val original_language: String
    abstract val backdrop_path: String?
    abstract val popularity: Float
    abstract val vote_count: Int
    abstract val vote_average: Float
}

@Serializable
class KnownForType1(
    override val poster_path: String?,
    val adult: Boolean,
    override val overview: String,
    val release_date: String,
    val original_title: String,
    override val genre_ids: List<Int>,
    override val id: Int,
    override val media_type: String,
    override val original_language: String,
    val title: String,
    override val backdrop_path: String?,
    override val popularity: Float,
    override val vote_count: Int,
    val video: Boolean,
    override val vote_average: Float
): KnownFor()

@Serializable
class KnownForType2(
    override val poster_path: String?,
    override val popularity: Float,
    override val id: Int,
    override val overview: String,
    override val backdrop_path: String?,
    override val vote_average: Float,
    override val media_type: String,
    val first_air_date: String,
    val origin_country: List<String>,
    override val genre_ids: List<Int>,
    override val original_language: String,
    override val vote_count: Int,
    val name: String,
    val original_name: String,
): KnownFor()