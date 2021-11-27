package com.avelycure.moviefan.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.avelycure.moviefan.data.local.dao.*
import com.avelycure.moviefan.data.local.entities.*
import com.avelycure.moviefan.data.local.type_converters.*

@Database(
    entities = arrayOf(
        EntityMovieInfo::class,
        EntityRemoteKeys::class,
        EntityPopularMovie::class,
        EntityPopularPerson::class,
        EntityRemoteKeysPopularPersons::class
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
    abstract fun cachePopularPersonsDao(): CachePopularPersonsDao
    abstract fun remoteKeysPopularPersonsDao(): RemoteKeysPopularPersonsDao
}