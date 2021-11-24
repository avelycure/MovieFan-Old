package com.avelycure.moviefan.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.avelycure.moviefan.data.local.dao.CacheMovieInfoDao
import com.avelycure.moviefan.data.local.dao.CachePopularMovieDao
import com.avelycure.moviefan.data.local.dao.RemoteKeysDao
import com.avelycure.moviefan.data.local.entities.EntityMovieInfo
import com.avelycure.moviefan.data.local.entities.EntityPopularMovie
import com.avelycure.moviefan.data.local.entities.EntityRemoteKeys
import com.avelycure.moviefan.data.local.type_converters.*

@Database(
    entities = arrayOf(
        EntityMovieInfo::class,
        EntityRemoteKeys::class,
        EntityPopularMovie::class
    ),
    version = 1
)
@TypeConverters(
    value = arrayOf(
        ConverterProductionCompanies::class,
        ConverterGenre::class,
        ConverterProductionCountries::class,
        ConverterLanguages::class,
        ConverterCast::class,
        ConverterGenreIds::class
    )
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cacheMovieInfoDao(): CacheMovieInfoDao
    abstract fun remoteKeysDao(): RemoteKeysDao
    abstract fun cachePopularMovieDao(): CachePopularMovieDao
}