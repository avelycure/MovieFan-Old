package com.avelycure.moviefan.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.avelycure.moviefan.data.local.type_converters.ConverterGenreIds
import com.avelycure.moviefan.domain.models.Movie

@Entity(tableName = "popular_movies")
data class EntityPopularMovie(
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

fun EntityPopularMovie.toMovie():Movie{
    return Movie(
        title = title,
        originalTitle = originalTitle,
        posterPath = posterPath,
        genreIds = genreIds,
        popularity = popularity,
        voteAverage = voteAverage,
        releaseDate = releaseDate,
        movieId = movieId,
        voteCount = voteCount
    )
}