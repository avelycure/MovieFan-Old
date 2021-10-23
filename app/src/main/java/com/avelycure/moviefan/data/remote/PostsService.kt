package com.avelycure.moviefan.data.remote

import com.avelycure.moviefan.data.remote.dto.popular.PolularMoviesResponse
import com.avelycure.moviefan.data.remote.dto.VideosResponse

interface PostsService {
    suspend fun getPosts(nextPage: Int): PolularMoviesResponse
    suspend fun getVideos(id: Int): VideosResponse
}