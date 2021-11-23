package com.avelycure.moviefan.di.modules

import android.app.Application
import androidx.room.Room
import com.avelycure.moviefan.data.local.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CacheModule {
    @Provides
    @Singleton
    fun provideCacheDao(appDatabase: AppDatabase) = appDatabase.cacheDao()

    @Provides
    @Singleton
    fun provideAppDatabase(app: Application) = Room.databaseBuilder(
        app, AppDatabase::class.java, "database"
    ).build()
}