package com.avelycure.moviefan.data.remote.service.movies.videos

import com.avelycure.moviefan.data.remote.dto.video.VideosResponse

interface IVideosService {
    suspend fun getVideos(id: Int): VideosResponse
}