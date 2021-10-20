package com.avelycure.moviefan.data.remote

import com.avelycure.moviefan.common.Constants
import com.avelycure.moviefan.data.remote.dto.PolularMoviesResponse
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.request.*

class PostsServiceImpl(
    private val client: HttpClient
) : PostsService {

    override suspend fun getPosts(): PolularMoviesResponse {
        return try {
            client.get { url(Constants.POPULAR_MOVIES) }
        } catch (e: RedirectResponseException) {
            //3xx
            println("Error: ${e.response.status.description}")
            PolularMoviesResponse()
        } catch (e: ClientRequestException) {
            //4xx
            println("Error: ${e.response.status.description}")
            PolularMoviesResponse()
        } catch (e: ServerResponseException) {
            //5xx
            println("Error: ${e.response.status.description}")
            PolularMoviesResponse()
        } catch (e: Exception) {
            println("Error: ${e.message}")
            PolularMoviesResponse()
        }
    }
}