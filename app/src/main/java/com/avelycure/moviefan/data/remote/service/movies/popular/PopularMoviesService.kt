package com.avelycure.moviefan.data.remote.service.movies.popular

import com.avelycure.moviefan.common.RequestConstants
import com.avelycure.moviefan.data.remote.dto.movie.MoviesResponse
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.request.*
import io.ktor.utils.io.errors.*

class PopularMoviesService(
    private val client: HttpClient
) : IPopularMoviesService {

    override suspend fun getPopularMovies(nextPage: Int): MoviesResponse {
        return try {
            client.get {
                with(RequestConstants) {
                    url("$BASE_URL/$POPULAR_MOVIES?api_key=$API_KEY&page=$nextPage")
                }
            }
        } catch (e: RedirectResponseException) {
            throw Exception("Further action needs to be taken in order to complete the request")
        } catch (e: ClientRequestException) {
            throw Exception("The request contains bad syntax or cannot be fulfilled")
        } catch (e: ServerResponseException) {
            throw Exception("The server failed to fulfil an apparently valid request")
        } catch (e: IOException) {
            throw Exception("No internet connection")
        } catch (e: Exception) {
            throw Exception(" Unknown error occurred")
        }
    }
}