package com.avelycure.moviefan.di.modules

import com.avelycure.moviefan.data.repository.MovieRepository
import com.avelycure.moviefan.domain.interactors.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object InteractorsModule {

    @Provides
    fun provideGetPopularMovies(repository: MovieRepository): GetPopularMovies {
        return GetPopularMovies(repository)
    }

    @Provides
    fun provideGetDetails(repository: MovieRepository): GetDetails {
        return GetDetails(repository)
    }

    @Provides
    fun provideGetVideos(repository: MovieRepository): GetTrailerCode {
        return GetTrailerCode(repository)
    }

    @Provides
    fun provideSearchMovie(repository: MovieRepository): SearchMovie {
        return SearchMovie(repository)
    }

    @Provides
    fun provideSaveToCache(repository: MovieRepository):SaveToCache{
        return SaveToCache(repository)
    }

    @Provides
    fun provideGetPerson(repository: MovieRepository):GetPerson{
        return GetPerson(repository)
    }
}