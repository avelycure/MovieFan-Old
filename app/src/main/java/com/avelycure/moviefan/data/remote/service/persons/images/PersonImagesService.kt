package com.avelycure.moviefan.data.remote.service.persons.images

import com.avelycure.moviefan.common.RequestConstants
import com.avelycure.moviefan.data.remote.dto.person.ResponsePersonImages
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.request.*
import io.ktor.utils.io.errors.*

class PersonImagesService(
    private val client: HttpClient
) : IPersonImagesService {
    override suspend fun getPersonImages(id: Int): ResponsePersonImages {
        return try {
            client.get {
                with(RequestConstants) {
                    url("$BASE_URL/person/$id/images?api_key=$API_KEY")
                }
            }
        } catch (e: RedirectResponseException) {
            throw Exception("Further action needs to be taken in order to complete the request")
        } catch (e: ClientRequestException) {
            throw Exception("The request contains bad syntax or cannot be fulfilled")
        } catch (e: ServerResponseException) {
            throw Exception("The server failed to fulfil an apparently valid request")
        } catch (e: IOException) {
            throw IOException("No internet connection")
        } catch (e: Exception) {
            throw Exception(" Unknown error occurred")
        }
    }
}