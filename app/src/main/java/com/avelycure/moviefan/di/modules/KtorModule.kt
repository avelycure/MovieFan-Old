package com.avelycure.moviefan.di.modules

import com.avelycure.moviefan.data.remote.PostsService
import com.avelycure.moviefan.data.remote.PostsServiceImpl
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
    fun provideKtorInstance(): PostsService {
        return PostsServiceImpl(
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