package com.avelycure.moviefan.data.remote.service

import com.avelycure.moviefan.data.remote.service.movies.IMoviesService
import com.avelycure.moviefan.data.remote.service.persons.images.IPersonImagesService
import com.avelycure.moviefan.data.remote.service.persons.person_info.IPersonsInfoService
import com.avelycure.moviefan.data.remote.service.persons.popular.IPopularPersonsService
import com.avelycure.moviefan.data.remote.service.persons.search.ISearchPersonsService

class ServiceFactory(
    val personImagesService: IPersonImagesService,
    val personInfoService: IPersonsInfoService,
    val popularPersonsService: IPopularPersonsService,
    val searchPersonsService: ISearchPersonsService,

    val moviesService: IMoviesService
)