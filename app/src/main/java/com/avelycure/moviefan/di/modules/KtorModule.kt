package com.avelycure.moviefan.di.modules

import com.avelycure.moviefan.data.remote.dto.PostsServiceImpl
import dagger.Module
import dagger.Provides
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*

@Module
class KtorModule {

    @Provides
    fun provideKtorInstance(): PostsServiceImpl {
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