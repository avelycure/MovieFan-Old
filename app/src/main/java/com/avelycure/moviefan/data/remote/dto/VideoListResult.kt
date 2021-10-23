package com.avelycure.moviefan.data.remote.dto

import com.avelycure.moviefan.domain.VideoInfo
import kotlinx.serialization.Serializable

@Serializable
data class VideoListResult(
    val iso_639_1: String,
    val iso_3166_1: String,
    val name: String,
    val key: String,
    val site: String,
    val size: Int,
    val type: String,
    val official: Boolean,
    val published_at: String,
    val id: String
)

fun VideoListResult.toVideoInfo(): VideoInfo{
    return VideoInfo(
        key = key
    )
}