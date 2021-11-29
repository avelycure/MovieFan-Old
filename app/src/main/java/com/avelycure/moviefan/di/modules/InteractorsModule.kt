package com.avelycure.moviefan.di.modules

import com.avelycure.moviefan.data.repository.MovieRepository
import com.avelycure.moviefan.domain.interactors.home.*
import com.avelycure.moviefan.domain.interactors.person.GetPerson
import com.avelycure.moviefan.domain.interactors.person.GetPersonInfo
import com.avelycure.moviefan.domain.interactors.person.GetPopularPersons
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
    fun provideGetDetails(repository: MovieRepository): GetMovieInfo {
        return GetMovieInfo(repository)
    }

    @Provides
    fun provideGetVideos(repository: MovieRepository): GetTrailerCode {
        return GetTrailerCode(repository)
    }

    @Provides
    fun provideSearchMovie(repository: MovieRepository): FindMovie {
        return FindMovie(repository)
    }

    @Provides
    fun provideSaveToCache(repository: MovieRepository): SaveMovieInfoToCache {
        return SaveMovieInfoToCache(repository)
    }

    @Provides
    fun provideGetPerson(repository: MovieRepository): GetPerson {
        return GetPerson(repository)
    }

    @Provides
    fun provideGetPersonInfo(repository: MovieRepository): GetPersonInfo {
        return GetPersonInfo(repository)
    }

    @Provides
    fun provideGetPopularPersons(repository: MovieRepository): GetPopularPersons {
        return GetPopularPersons(repository)
    }
}