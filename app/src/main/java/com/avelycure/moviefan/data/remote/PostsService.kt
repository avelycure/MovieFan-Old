package com.avelycure.moviefan.data.remote

import com.avelycure.moviefan.data.remote.dto.PolularMoviesResponse

interface PostsService {
    suspend fun getPosts(): PolularMoviesResponse
}