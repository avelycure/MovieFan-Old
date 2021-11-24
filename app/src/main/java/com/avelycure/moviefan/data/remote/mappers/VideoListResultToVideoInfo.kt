package com.avelycure.moviefan.data.remote.dto.video.mappers

import com.avelycure.moviefan.data.remote.dto.video.VideoListResult
import com.avelycure.moviefan.domain.models.VideoInfo

fun VideoListResult.toVideoInfo(): VideoInfo {
    return VideoInfo(
        key = key
    )
}