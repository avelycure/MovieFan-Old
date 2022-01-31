package com.avelycure.moviefan.di.modules

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
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PersonsModule {
    @Provides
    @Singleton
    fun providePersonImagesInstance(customClient: HttpClient): IPersonImagesService {
        return PersonImagesService(
            client = customClient
        )
    }

    @Provides
    @Singleton
    fun providePersonInfoInstance(customClient: HttpClient): IPersonInfoService {
        return PersonInfoService(
            client = customClient
        )
    }

    @Provides
    @Singleton
    fun providePopularPersonsInstance(customClient: HttpClient): IPopularPersonsService {
        return PopularPersonsService(
            client = customClient
        )
    }

    @Provides
    @Singleton
    fun provideSearchPersonsInstance(customClient: HttpClient): ISearchPersonsService {
        return SearchPersonsService(
            client = customClient
        )
    }
}