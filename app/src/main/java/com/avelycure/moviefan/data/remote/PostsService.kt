package com.avelycure.moviefan.data.remote

import com.avelycure.moviefan.data.remote.dto.details.DetailResponse
import com.avelycure.moviefan.data.remote.dto.popular.PolularMoviesResponse
import com.avelycure.moviefan.data.remote.dto.video.VideosResponse

interface PostsService {
    suspend fun getPosts(nextPage: Int): PolularMoviesResponse
    suspend fun getVideos(id: Int): VideosResponse
    suspend fun getMovieDetail(id: Int): DetailResponse
}