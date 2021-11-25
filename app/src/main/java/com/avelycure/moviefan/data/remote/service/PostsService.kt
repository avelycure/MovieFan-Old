package com.avelycure.moviefan.data.remote.service

import com.avelycure.moviefan.common.Constants
import com.avelycure.moviefan.data.remote.dto.details.DetailResponse
import com.avelycure.moviefan.data.remote.dto.movie.MoviesResponse
import com.avelycure.moviefan.data.remote.dto.person.ResponsePersonImages
import com.avelycure.moviefan.data.remote.dto.video.VideosResponse
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.request.*
import io.ktor.utils.io.errors.*

class PostsService(
    private val client: HttpClient
) : IPostsService {

    override suspend fun getPopularMovies(nextPage: Int): MoviesResponse {
        return try {
            client.get {
                url("${Constants.BASE_URL}/${Constants.POPULAR_MOVIES}${Constants.API_KEY}&page=$nextPage")
            }
        } catch (e: RedirectResponseException) {
            throw Exception("Further action needs to be taken in order to complete the request")
        } catch (e: ClientRequestException) {
            throw Exception("The request contains bad syntax or cannot be fulfilled")
        } catch (e: ServerResponseException) {
            throw Exception("The server failed to fulfil an apparently valid request")
        } catch (e: IOException) {
            throw Exception("No internet connection")
        }
    }

    override suspend fun getVideos(id: Int): VideosResponse {
        return try {
            client.get {
                url("${Constants.BASE_URL}/movie/$id/videos${Constants.API_KEY}")
            }
        } catch (e: RedirectResponseException) {
            throw Exception("Further action needs to be taken in order to complete the request")
        } catch (e: ClientRequestException) {
            throw Exception("The request contains bad syntax or cannot be fulfilled")
        } catch (e: ServerResponseException) {
            throw Exception("The server failed to fulfil an apparently valid request")
        } catch (e: IOException) {
            throw Exception("No internet connection")
        }
    }

    override suspend fun getMovieDetail(id: Int): DetailResponse {
        return try {
            client.get {
                url("${Constants.BASE_URL}/movie/$id${Constants.API_KEY}&${Constants.CREDITS},${Constants.MOVIE_IMAGES}")
            }
        } catch (e: RedirectResponseException) {
            throw Exception("Further action needs to be taken in order to complete the request")
        } catch (e: ClientRequestException) {
            throw Exception("The request contains bad syntax or cannot be fulfilled")
        } catch (e: ServerResponseException) {
            throw Exception("The server failed to fulfil an apparently valid request")
        } catch (e: IOException) {
            throw IOException("No internet connection")
        }
    }

    override suspend fun getMovies(query: String, page: Int): MoviesResponse {
        return try {
            client.get {
                url("${Constants.BASE_URL}/search/movie${Constants.API_KEY}&query=$query&page=$page")
            }
        } catch (e: RedirectResponseException) {
            throw Exception("Further action needs to be taken in order to complete the request")
        } catch (e: ClientRequestException) {
            throw Exception("The request contains bad syntax or cannot be fulfilled")
        } catch (e: ServerResponseException) {
            throw Exception("The server failed to fulfil an apparently valid request")
        } catch (e: IOException) {
            throw Exception("No internet connection")
        }
    }

    override suspend fun getPersonImages(id: Int): ResponsePersonImages {
        return try {
            client.get {
                url("${Constants.BASE_URL}/person/$id/images${Constants.API_KEY}")
            }
        } catch (e: RedirectResponseException) {
            throw Exception("Further action needs to be taken in order to complete the request")
        } catch (e: ClientRequestException) {
            throw Exception("The request contains bad syntax or cannot be fulfilled")
        } catch (e: ServerResponseException) {
            throw Exception("The server failed to fulfil an apparently valid request")
        } catch (e: IOException) {
            throw IOException("No internet connection")
        }
    }
}