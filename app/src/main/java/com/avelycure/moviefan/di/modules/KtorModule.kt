package com.avelycure.moviefan.di.modules

import com.avelycure.moviefan.data.local.AppDatabase
import com.avelycure.moviefan.data.local.dao.CacheMovieInfoDao
import com.avelycure.moviefan.data.remote.service.ServiceFactory
import com.avelycure.moviefan.data.repository.MovieRepository
import com.avelycure.moviefan.data.remote.service.movies.info.MovieInfoService
import com.avelycure.moviefan.data.remote.service.movies.info.IMovieInfoService
import com.avelycure.moviefan.data.remote.service.movies.popular.PopularMoviesService
import com.avelycure.moviefan.data.remote.service.movies.popular.IPopularMoviesService
import com.avelycure.moviefan.data.remote.service.movies.search.ISearchMoviesService
import com.avelycure.moviefan.data.remote.service.movies.search.SearchMoviesService
import com.avelycure.moviefan.data.remote.service.movies.videos.VideosService
import com.avelycure.moviefan.data.remote.service.movies.videos.IVideosService
import com.avelycure.moviefan.data.remote.service.persons.images.IPersonImagesService
import com.avelycure.moviefan.data.remote.service.persons.images.PersonImagesService
import com.avelycure.moviefan.data.remote.service.persons.info.IPersonInfoService
import com.avelycure.moviefan.data.remote.service.persons.info.PersonInfoService
import com.avelycure.moviefan.data.remote.service.persons.popular.IPopularPersonsService
import com.avelycure.moviefan.data.remote.service.persons.popular.PopularPersonsService
import com.avelycure.moviefan.data.remote.service.persons.search.ISearchPersonsService
import com.avelycure.moviefan.data.remote.service.persons.search.SearchPersonsService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object KtorModule {
    @Provides
    @Singleton
    fun provideGetMovieInfoInstance(): IMovieInfoService {
        return MovieInfoService(
            HttpClient(Android) {
                install(Logging) {
                    level = LogLevel.ALL
                }
                install(JsonFeature) {
                    serializer = KotlinxSerializer()
                }
            }
        )
    }

    @Provides
    @Singleton
    fun providePopularMoviesInstance(): IPopularMoviesService {
        return PopularMoviesService(
            HttpClient(Android) {
                install(Logging) {
                    level = LogLevel.ALL
                }
                install(JsonFeature) {
                    serializer = KotlinxSerializer()
                }
            }
        )
    }

    @Provides
    @Singleton
    fun provideSearchMoviesInstance(): ISearchMoviesService {
        return SearchMoviesService(
            HttpClient(Android) {
                install(Logging) {
                    level = LogLevel.ALL
                }
                install(JsonFeature) {
                    serializer = KotlinxSerializer()
                }
            }
        )
    }

    @Provides
    @Singleton
    fun provideGetVideosInstance(): IVideosService {
        return VideosService(
            HttpClient(Android) {
                install(Logging) {
                    level = LogLevel.ALL
                }
                install(JsonFeature) {
                    serializer = KotlinxSerializer()
                }
            }
        )
    }

    @Provides
    @Singleton
    fun providePersonImagesInstance(): IPersonImagesService {
        return PersonImagesService(
            HttpClient(Android) {
                install(Logging) {
                    level = LogLevel.ALL
                }
                install(JsonFeature) {
                    serializer = KotlinxSerializer()
                }
            }
        )
    }

    @Provides
    @Singleton
    fun providePersonInfoInstance(): IPersonInfoService {
        return PersonInfoService(
            HttpClient(Android) {
                install(Logging) {
                    level = LogLevel.ALL
                }
                install(JsonFeature) {
                    serializer = KotlinxSerializer()
                }
            }
        )
    }

    @Provides
    @Singleton
    fun providePopularPersonsInstance(): IPopularPersonsService {
        return PopularPersonsService(
            HttpClient(Android) {
                install(Logging) {
                    level = LogLevel.ALL
                }
                install(JsonFeature) {
                    serializer = KotlinxSerializer()
                }
            }
        )
    }

    @Provides
    @Singleton
    fun provideSearchPersonsInstance(): ISearchPersonsService {
        return SearchPersonsService(
            HttpClient(Android) {
                install(Logging) {
                    level = LogLevel.ALL
                }
                install(JsonFeature) {
                    serializer = KotlinxSerializer()
                }
            }
        )
    }

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

    @Provides
    @Singleton
    fun provideMovieRepository(
        cacheMovieInfoDao: CacheMovieInfoDao,
        appDatabase: AppDatabase,
        serviceFactory: ServiceFactory
    ): MovieRepository {
        return MovieRepository(cacheMovieInfoDao, appDatabase, serviceFactory)
    }
}