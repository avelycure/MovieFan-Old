package com.avelycure.moviefan.data.remote

import com.avelycure.moviefan.data.remote.dto.details.DetailResponse
import com.avelycure.moviefan.data.remote.dto.popular.PopularMoviesResponse
import com.avelycure.moviefan.data.remote.dto.video.VideosResponse

interface IPostsService {
    suspend fun getPosts(nextPage: Int): PopularMoviesResponse
    suspend fun getVideos(id: Int): VideosResponse
    suspend fun getMovieDetail(id: Int): DetailResponse
    suspend fun searchMovies(query: String): PopularMoviesResponse
}