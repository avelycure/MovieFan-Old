package com.avelycure.moviefan.data.remote.service.persons.search

import com.avelycure.moviefan.common.RequestConstants
import com.avelycure.moviefan.data.remote.dto.search_person.ResponseSearchPerson
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.request.*
import io.ktor.utils.io.errors.*

class SearchPersonsService(
    private val client:HttpClient
):ISearchPersonsService {
    override suspend fun getPersonsByName(query: String, page: Int): ResponseSearchPerson {
        return try {
            client.get {
                with(RequestConstants) {
                    url("$BASE_URL/search/person?api_key=$API_KEY&query=${query}&page=${page}")
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