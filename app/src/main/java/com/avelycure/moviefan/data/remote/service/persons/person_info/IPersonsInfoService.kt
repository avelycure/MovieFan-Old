package com.avelycure.moviefan.data.remote.service.persons.person_info

import com.avelycure.moviefan.data.remote.dto.person.ResponsePersonInfo

interface IPersonsInfoService {
    suspend fun getPersonInfo(id: Int): ResponsePersonInfo

}