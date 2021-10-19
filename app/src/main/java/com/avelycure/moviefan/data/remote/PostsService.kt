package com.avelycure.moviefan.data.remote

import com.avelycure.moviefan.data.remote.model.PolularMoviesResponse

interface PostsService {
    suspend fun getPosts(): PolularMoviesResponse
}