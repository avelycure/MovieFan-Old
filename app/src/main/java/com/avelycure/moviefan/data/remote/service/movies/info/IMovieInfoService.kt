package com.avelycure.moviefan.data.remote.service.movies.info

import com.avelycure.moviefan.data.remote.dto.details.DetailResponse

interface IMovieInfoService {
    suspend fun getMovieDetail(id: Int): DetailResponse
}