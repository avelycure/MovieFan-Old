package com.avelycure.moviefan.di.modules

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.features.json.serializer.*
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object KtorModule {

    @Provides
    @Singleton
    fun provideSerializer(): KotlinxSerializer {
        return KotlinxSerializer(
            Json{
                ignoreUnknownKeys = true
            }
        )
    }
}