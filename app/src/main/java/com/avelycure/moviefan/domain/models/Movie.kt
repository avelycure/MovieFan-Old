package com.avelycure.moviefan.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.avelycure.moviefan.data.local.type_converters.ConverterGenreIds

/**
 * Class which represents item in recycler view
 */
@Entity(tableName = "popular_movies")
data class Movie(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
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
