package com.avelycure.moviefan.data.remote.dto

import com.avelycure.moviefan.common.Constants
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.request.*

class PostsServiceImpl(
    private val client: HttpClient
) : PostsService {

    override suspend fun getPosts(): List<PolularMoviesResponse> {
        return try {
            client.get { url(Constants.POPULAR_MOVIES) }
        } catch (e: RedirectResponseException) {
            //3xx
            println("Error: ${e.response.status.description}")
            emptyList()
        } catch (e: ClientRequestException) {
            //4xx
            println("Error: ${e.response.status.description}")
            emptyList()
        } catch (e: ServerResponseException) {
            //5xx
            println("Error: ${e.response.status.description}")
            emptyList()
        } catch (e: Exception) {
            println("Error: ${e.message}")
            emptyList()
        }
    }
}