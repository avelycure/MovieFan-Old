package com.avelycure.moviefan.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.avelycure.moviefan.data.local.type_converters.ConverterGenreIds
import com.avelycure.moviefan.domain.models.Movie
import com.avelycure.moviefan.domain.models.MovieInfo

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


fun EntityPopularMovie.toMovie(): Movie {
    return Movie(
        originalTitle = originalTitle,
        popularity = popularity,
        releaseDate = releaseDate,
        voteAverage = voteAverage,
        title = title,
        voteCount = voteCount,
        posterPath = posterPath,
        movieId = movieId,
        genreIds = genreIds
    )
}
