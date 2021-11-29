package com.avelycure.moviefan.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.avelycure.moviefan.data.local.type_converters.ConverterListInt

@Entity(tableName = "movies")
data class EntityMovie(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val originalTitle: String,
    val posterPath: String?,
    @TypeConverters(ConverterListInt::class)
    val genreIds: List<Int>,
    val popularity: Float,
    val voteAverage: Float,
    val releaseDate: String,
    val movieId: Int,
    val voteCount: Int
)