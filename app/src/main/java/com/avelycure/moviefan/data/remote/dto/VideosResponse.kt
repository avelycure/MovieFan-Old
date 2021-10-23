package com.avelycure.moviefan.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class VideosResponse(
    val id: Int,
    val results: List<VideoListResult>
)
