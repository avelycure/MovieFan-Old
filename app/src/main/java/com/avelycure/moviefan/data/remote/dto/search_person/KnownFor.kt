package com.avelycure.moviefan.data.remote.dto.search_person

import com.avelycure.moviefan.utils.network.KnownForSerializer
import kotlinx.serialization.Serializable

@Serializable(with = KnownForSerializer::class)
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