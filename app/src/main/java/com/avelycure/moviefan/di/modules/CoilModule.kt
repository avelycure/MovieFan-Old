package com.avelycure.moviefan.di.modules

import android.app.Application
import coil.ImageLoader
import com.avelycure.moviefan.R
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoilModule {
    @Provides
    @Singleton
    fun provideImageLoader(app: Application): ImageLoader {
        return ImageLoader.Builder(app)
            .error(R.drawable.image_placeholder)
            .placeholder(R.drawable.image_placeholder)
            .availableMemoryPercentage(0.25)
            .crossfade(true)
            .build()
    }
}