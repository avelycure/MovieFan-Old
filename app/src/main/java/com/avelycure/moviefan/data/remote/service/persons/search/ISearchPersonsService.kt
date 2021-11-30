package com.avelycure.moviefan.data.remote.service.persons.search

import com.avelycure.moviefan.data.remote.dto.search_person.ResponseSearchPerson

interface ISearchPersonsService {
    suspend fun getPersonsByName(query: String, page: Int): ResponseSearchPerson
}