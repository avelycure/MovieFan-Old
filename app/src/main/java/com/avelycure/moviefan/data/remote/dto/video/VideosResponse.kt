package com.avelycure.moviefan.data.remote.dto.video

import kotlinx.serialization.Serializable

@Serializable
data class VideosResponse(
    val id: Int,
    val results: List<VideoListResult>
)
