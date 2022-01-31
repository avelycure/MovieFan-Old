package com.avelycure.moviefan.di.modules

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object KtorModule {

    @Provides
    @Singleton
    fun provideSerializer(): KotlinxSerializer {
        return KotlinxSerializer(
            Json {
                ignoreUnknownKeys = true
            }
        )
    }

    @Provides
    @Singleton
    fun provideClient(customSerializer: KotlinxSerializer): HttpClient {
        return HttpClient(Android) {
            install(Logging) {
                level = LogLevel.ALL
            }
            install(JsonFeature) {
                serializer = customSerializer
            }
        }
    }
}