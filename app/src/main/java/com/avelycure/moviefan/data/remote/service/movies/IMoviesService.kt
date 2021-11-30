package com.avelycure.moviefan.data.remote.service.movies

import com.avelycure.moviefan.data.remote.dto.details.DetailResponse
import com.avelycure.moviefan.data.remote.dto.movie.MoviesResponse
import com.avelycure.moviefan.data.remote.dto.person.ResponsePersonInfo
import com.avelycure.moviefan.data.remote.dto.person.ResponsePersonImages
import com.avelycure.moviefan.data.remote.dto.search_person.ResponseSearchPerson
import com.avelycure.moviefan.data.remote.dto.video.VideosResponse

interface IMoviesService {
    suspend fun getMoviesByName(query: String, page: Int): MoviesResponse
    suspend fun getPopularMovies(nextPage: Int): MoviesResponse
    suspend fun getMovieDetail(id: Int): DetailResponse
    suspend fun getVideos(id: Int): VideosResponse
}