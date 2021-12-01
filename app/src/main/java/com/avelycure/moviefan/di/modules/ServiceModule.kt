package com.avelycure.moviefan.di.modules

import com.avelycure.moviefan.data.remote.service.ServiceFactory
import com.avelycure.moviefan.data.remote.service.movies.info.IMovieInfoService
import com.avelycure.moviefan.data.remote.service.movies.popular.IPopularMoviesService
import com.avelycure.moviefan.data.remote.service.movies.search.ISearchMoviesService
import com.avelycure.moviefan.data.remote.service.movies.videos.IVideosService
import com.avelycure.moviefan.data.remote.service.persons.images.IPersonImagesService
import com.avelycure.moviefan.data.remote.service.persons.info.IPersonInfoService
import com.avelycure.moviefan.data.remote.service.persons.popular.IPopularPersonsService
import com.avelycure.moviefan.data.remote.service.persons.search.ISearchPersonsService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {
    @Provides
    @Singleton
    fun provideServiceFactory(
        personImagesService: IPersonImagesService,
        personInfoService: IPersonInfoService,
        popularPersonsService: IPopularPersonsService,
        searchPersonsService: ISearchPersonsService,

        movieInfoService: IMovieInfoService,
        popularMoviesService: IPopularMoviesService,
        searchMoviesService: ISearchMoviesService,
        videosService: IVideosService
    ): ServiceFactory {
        return ServiceFactory(
            personImagesService,
            personInfoService,
            popularPersonsService,
            searchPersonsService,
            movieInfoService,
            popularMoviesService,
            searchMoviesService,
            videosService
        )
    }
}