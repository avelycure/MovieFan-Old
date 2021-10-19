package com.avelycure.moviefan.data.remote.dto

interface PostsService {
    suspend fun getPosts(): PolularMoviesResponse
}