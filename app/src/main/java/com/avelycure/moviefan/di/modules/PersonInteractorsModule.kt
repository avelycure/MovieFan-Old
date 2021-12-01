package com.avelycure.moviefan.di.modules

import com.avelycure.moviefan.data.repository.MovieRepository
import com.avelycure.moviefan.domain.interactors.person.GetPerson
import com.avelycure.moviefan.domain.interactors.person.GetPersonInfo
import com.avelycure.moviefan.domain.interactors.person.GetPopularPersons
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object PersonInteractorsModule {
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