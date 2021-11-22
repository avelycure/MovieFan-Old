package com.avelycure.moviefan.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.avelycure.moviefan.data.local.dao.CacheDao
import com.avelycure.moviefan.data.local.entities.EntityMovie
import com.avelycure.moviefan.data.local.type_converters.*

@Database(
    entities = arrayOf(
        EntityMovie::class
    ),
    version = 1
)
@TypeConverters(
    value = arrayOf(
        ConverterProductionCompanies::class,
        ConverterGenre::class,
        ConverterProductionCountries::class,
        ConverterLanguages::class,
        ConverterCast::class
    )
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cacheDao(): CacheDao
}