package com.avelycure.moviefan.utils.network

import com.avelycure.moviefan.data.remote.dto.search_person.KnownFor
import com.avelycure.moviefan.data.remote.dto.search_person.KnownForMovie
import com.avelycure.moviefan.data.remote.dto.search_person.KnownForTv
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.json.JsonContentPolymorphicSerializer
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

object KnownForSerializer : JsonContentPolymorphicSerializer<KnownFor>(KnownFor::class) {
    override fun selectDeserializer(element: JsonElement): DeserializationStrategy<out KnownFor> {
        return when (element.jsonObject["media_type"]?.jsonPrimitive?.content) {
            "movie" -> KnownForMovie.serializer()
            "tv" -> KnownForTv.serializer()
            else -> throw Exception("Unknown Module: key 'type' not found or does not matches any module type")
        }
    }
}