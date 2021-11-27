package com.avelycure.moviefan.data.remote.service

import com.avelycure.moviefan.data.remote.dto.details.DetailResponse
import com.avelycure.moviefan.data.remote.dto.movie.MoviesResponse
import com.avelycure.moviefan.data.remote.dto.person.ResponsePersonInfo
import com.avelycure.moviefan.data.remote.dto.person.ResponsePersonImages
import com.avelycure.moviefan.data.remote.dto.search_person.ResponseSearchPerson
import com.avelycure.moviefan.data.remote.dto.video.VideosResponse

interface IPostsService {
    suspend fun getMovies(query: String, page: Int): MoviesResponse
    suspend fun getPopularMovies(nextPage: Int): MoviesResponse
    suspend fun getMovieDetail(id: Int): DetailResponse
    suspend fun getVideos(id: Int): VideosResponse
    suspend fun getPersonImages(id: Int): ResponsePersonImages
    suspend fun getPersons(query: String, page: Int): ResponseSearchPerson
    suspend fun getPersonInfo(id: Int): ResponsePersonInfo
    suspend fun getPopularPerson(): ResponsePersonInfo
}