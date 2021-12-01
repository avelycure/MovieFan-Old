package com.avelycure.moviefan.data.remote.service.persons.info

import com.avelycure.moviefan.data.remote.dto.person.ResponsePersonInfo

interface IPersonInfoService {
    suspend fun getPersonInfo(id: Int): ResponsePersonInfo

}