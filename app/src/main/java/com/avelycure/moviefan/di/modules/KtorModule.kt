package com.avelycure.moviefan.di.modules

import com.avelycure.moviefan.data.local.AppDatabase
import com.avelycure.moviefan.data.local.dao.CacheMovieInfoDao
import com.avelycure.moviefan.data.remote.service.ServiceFactory
import com.avelycure.moviefan.data.repository.MovieRepository
import com.avelycure.moviefan.data.remote.service.movies.IMoviesService
import com.avelycure.moviefan.data.remote.service.movies.MoviesService
import com.avelycure.moviefan.data.remote.service.persons.images.IPersonImagesService
import com.avelycure.moviefan.data.remote.service.persons.images.PersonImagesService
import com.avelycure.moviefan.data.remote.service.persons.person_info.IPersonsInfoService
import com.avelycure.moviefan.data.remote.service.persons.person_info.PersonsInfoService
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
    fun provideMoviesInstance(): IMoviesService {
        return MoviesService(
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
    fun providePersonInfoInstance(): IPersonsInfoService {
        return PersonsInfoService(
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
        personInfoService: IPersonsInfoService,
        popularPersonsService: IPopularPersonsService,
        searchPersonsService: ISearchPersonsService,

        moviesService: IMoviesService
    ): ServiceFactory {
        return ServiceFactory(
            personImagesService,
            personInfoService,
            popularPersonsService,
            searchPersonsService,
            moviesService
        )
    }

    @Provides
    @Singleton
    fun provideMovieRepository(
        moviesService: IMoviesService,
        cacheMovieInfoDao: CacheMovieInfoDao,
        appDatabase: AppDatabase,
        serviceFactory: ServiceFactory
    ): MovieRepository {
        return MovieRepository(moviesService, cacheMovieInfoDao, appDatabase, serviceFactory)
    }
}