package com.avelycure.moviefan.data.remote.dto.search_person

import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonContentPolymorphicSerializer
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

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

@Serializable
class KnownForType1(
    override val poster_path: String? = "",
    val adult: Boolean = false,
    override val overview: String = "",
    val release_date: String = "",
    val original_title: String = "",
    override val genre_ids: List<Int> = emptyList(),
    override val id: Int = 0,
    override val media_type: String = "",
    override val original_language: String = "",
    val title: String = "",
    override val backdrop_path: String? = "",
    override val popularity: Float = 0f,
    override val vote_count: Int = 0,
    val video: Boolean = false,
    override val vote_average: Float = 0f
) : KnownFor()

@Serializable
class KnownForType2(
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

object KnownForSerializer : JsonContentPolymorphicSerializer<KnownFor>(KnownFor::class) {
    override fun selectDeserializer(element: JsonElement): DeserializationStrategy<out KnownFor> {
        return when (element.jsonObject["media_type"]?.jsonPrimitive?.content) {
            "movie" -> KnownForType1.serializer()
            "tv" -> KnownForType2.serializer()
            else -> throw Exception("Unknown Module: key 'type' not found or does not matches any module type")
        }
    }
}