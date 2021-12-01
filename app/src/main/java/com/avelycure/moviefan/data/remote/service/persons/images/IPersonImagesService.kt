package com.avelycure.moviefan.data.remote.service.persons.images

import com.avelycure.moviefan.data.remote.dto.person.ResponsePersonImages

interface IPersonImagesService {
    suspend fun getPersonImages(id: Int): ResponsePersonImages
}