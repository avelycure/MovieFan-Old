package com.avelycure.moviefan.data.remote.service

import com.avelycure.moviefan.data.remote.service.movies.info.IMovieInfoService
import com.avelycure.moviefan.data.remote.service.movies.popular.IPopularMoviesService
import com.avelycure.moviefan.data.remote.service.movies.search.ISearchMoviesService
import com.avelycure.moviefan.data.remote.service.movies.videos.IVideosService
import com.avelycure.moviefan.data.remote.service.persons.images.IPersonImagesService
import com.avelycure.moviefan.data.remote.service.persons.info.IPersonInfoService
import com.avelycure.moviefan.data.remote.service.persons.popular.IPopularPersonsService
import com.avelycure.moviefan.data.remote.service.persons.search.ISearchPersonsService

class ServiceFactory(
    val personImagesService: IPersonImagesService,
    val personInfoService: IPersonInfoService,
    val popularPersonsService: IPopularPersonsService,
    val searchPersonsService: ISearchPersonsService,

    val movieInfoService: IMovieInfoService,
    val popularMoviesService: IPopularMoviesService,
    val searchMoviesService: ISearchMoviesService,
    val videosService: IVideosService
)