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
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PersonsModule {
    @Provides
    @Singleton
    fun providePersonImagesInstance(customSerializer: KotlinxSerializer): IPersonImagesService {
        return PersonImagesService(
            HttpClient(Android) {
                install(Logging) {
                    level = LogLevel.ALL
                }
                install(JsonFeature) {
                    serializer = customSerializer
                }
            }
        )
    }

    @Provides
    @Singleton
    fun providePersonInfoInstance(customSerializer: KotlinxSerializer): IPersonInfoService {
        return PersonInfoService(
            HttpClient(Android) {
                install(Logging) {
                    level = LogLevel.ALL
                }
                install(JsonFeature) {
                    serializer = customSerializer
                }
            }
        )
    }

    @Provides
    @Singleton
    fun providePopularPersonsInstance(customSerializer: KotlinxSerializer): IPopularPersonsService {
        return PopularPersonsService(
            HttpClient(Android) {
                install(Logging) {
                    level = LogLevel.ALL
                }
                install(JsonFeature) {
                    serializer = customSerializer
                }
            }
        )
    }

    @Provides
    @Singleton
    fun provideSearchPersonsInstance(customSerializer: KotlinxSerializer): ISearchPersonsService {
        return SearchPersonsService(
            HttpClient(Android) {
                install(Logging) {
                    level = LogLevel.ALL
                }
                install(JsonFeature) {
                    serializer = customSerializer
                }
            }
        )
    }
}