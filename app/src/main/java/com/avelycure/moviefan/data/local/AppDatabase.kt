package com.avelycure.moviefan.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.avelycure.moviefan.data.local.dao.CacheDao
import com.avelycure.moviefan.data.local.dao.RemoteKeysDao
import com.avelycure.moviefan.data.local.entities.EntityMovie
import com.avelycure.moviefan.data.local.entities.RemoteKeys
import com.avelycure.moviefan.data.local.type_converters.*
import com.avelycure.moviefan.domain.models.Movie

@Database(
    entities = arrayOf(
        EntityMovie::class,
        RemoteKeys::class,
        Movie::class
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
    abstract fun cacheDao(): CacheDao
    abstract fun remoteKeysDao(): RemoteKeysDao
}