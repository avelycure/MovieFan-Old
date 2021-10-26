package com.avelycure.moviefan.data.remote

import com.avelycure.moviefan.common.Constants
import com.avelycure.moviefan.data.remote.dto.details.DetailResponse
import com.avelycure.moviefan.data.remote.dto.popular.PolularMoviesResponse
import com.avelycure.moviefan.data.remote.dto.video.VideosResponse
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.request.*
import io.ktor.utils.io.errors.*

class PostsServiceImpl(
    private val client: HttpClient
) : IPostsService {

    override suspend fun getPosts(nextPage: Int): PolularMoviesResponse {
        return try {
            client.get {
                url(Constants.POPULAR_MOVIES + nextPage)
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
                url("${Constants.BASE_URL}/$id/videos?api_key=${Constants.API_KEY}")
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
                url("${Constants.BASE_URL}/$id?api_key=${Constants.API_KEY}${Constants.CREDITS}")
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
}