package com.avelycure.moviefan.di.modules

import com.avelycure.moviefan.data.remote.service.movies.info.IMovieInfoService
import com.avelycure.moviefan.data.remote.service.movies.info.MovieInfoService
import com.avelycure.moviefan.data.remote.service.movies.popular.IPopularMoviesService
import com.avelycure.moviefan.data.remote.service.movies.popular.PopularMoviesService
import com.avelycure.moviefan.data.remote.service.movies.search.ISearchMoviesService
import com.avelycure.moviefan.data.remote.service.movies.search.SearchMoviesService
import com.avelycure.moviefan.data.remote.service.movies.videos.IVideosService
import com.avelycure.moviefan.data.remote.service.movies.videos.VideosService
import com.avelycure.moviefan.presentation.home.adapters.MovieAdapter
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
    fun provideGetMovieInfoInstance(customClient: HttpClient): IMovieInfoService {
        return MovieInfoService(
            client = customClient
        )
    }

    @Provides
    @Singleton
    fun providePopularMoviesInstance(customClient: HttpClient): IPopularMoviesService {
        return PopularMoviesService(
            client = customClient
        )
    }

    @Provides
    @Singleton
    fun provideSearchMoviesInstance(customClient: HttpClient): ISearchMoviesService {
        return SearchMoviesService(
            client = customClient
        )
    }

    @Provides
    @Singleton
    fun provideGetVideosInstance(customClient: HttpClient): IVideosService {
        return VideosService(
            client = customClient
        )
    }

    @Provides
    @Singleton
    fun provideMovieAdapter(): MovieAdapter {
        return MovieAdapter()
    }
}