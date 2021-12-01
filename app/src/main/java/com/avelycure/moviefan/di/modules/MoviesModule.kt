package com.avelycure.moviefan.di.modules

import com.avelycure.moviefan.data.remote.service.movies.info.IMovieInfoService
import com.avelycure.moviefan.data.remote.service.movies.info.MovieInfoService
import com.avelycure.moviefan.data.remote.service.movies.popular.IPopularMoviesService
import com.avelycure.moviefan.data.remote.service.movies.popular.PopularMoviesService
import com.avelycure.moviefan.data.remote.service.movies.search.ISearchMoviesService
import com.avelycure.moviefan.data.remote.service.movies.search.SearchMoviesService
import com.avelycure.moviefan.data.remote.service.movies.videos.IVideosService
import com.avelycure.moviefan.data.remote.service.movies.videos.VideosService
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
object MoviesModule {
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
}