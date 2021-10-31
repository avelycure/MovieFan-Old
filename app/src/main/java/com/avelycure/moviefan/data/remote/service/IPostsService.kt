package com.avelycure.moviefan.data.remote.service

import com.avelycure.moviefan.data.remote.dto.details.DetailResponse
import com.avelycure.moviefan.data.remote.dto.movie.MoviesResponse
import com.avelycure.moviefan.data.remote.dto.video.VideosResponse

interface IPostsService {
    suspend fun getPosts(nextPage: Int): MoviesResponse
    suspend fun getVideos(id: Int): VideosResponse
    suspend fun getMovieDetail(id: Int): DetailResponse
    suspend fun searchMovies(query: String, page: Int): MoviesResponse
}