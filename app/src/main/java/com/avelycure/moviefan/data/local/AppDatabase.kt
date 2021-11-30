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
        EntityRemoteKeysMovies::class,
        EntityMovie::class,
        EntityPerson::class,
        EntityRemoteKeysPersons::class
    ),
    version = 1
)
@TypeConverters(
    value = arrayOf(
        ConverterListProductionCompanies::class,
        ConverterListMovieGenre::class,
        ConverterListProductionCountries::class,
        ConverterListSpokenLanguages::class,
        ConverterListString::class,
        ConverterListInt::class
    )
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cacheMovieInfoDao(): CacheMovieInfoDao
    abstract fun remoteKeysDao(): RemoteKeysMoviesDao
    abstract fun cachePopularMovieDao(): CacheMovieDao
    abstract fun cachePopularPersonsDao(): CachePersonsDao
    abstract fun remoteKeysPopularPersonsDao(): RemoteKeysPersonsDao
}