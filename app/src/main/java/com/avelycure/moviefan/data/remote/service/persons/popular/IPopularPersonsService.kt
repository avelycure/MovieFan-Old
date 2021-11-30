package com.avelycure.moviefan.data.remote.service.persons.popular

import com.avelycure.moviefan.data.remote.dto.search_person.ResponseSearchPerson

interface IPopularPersonsService {
    suspend fun getPopularPerson(page: Int): ResponseSearchPerson
}