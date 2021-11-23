package com.avelycure.moviefan.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.avelycure.moviefan.data.local.type_converters.ConverterGenreIds

@Entity(tableName = "popular")
data class EntityPopularMovie (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val dateOfSave: Long,
    val title: String,
    val originalTitle: String,
    val posterPath: String?,
    @TypeConverters(ConverterGenreIds::class)
    val genreIds: List<Int>,
    val popularity: Float,
    val voteAverage: Float,
    val releaseDate: String,
    val movieId: Int,
    val voteCount: Int
)