package com.avelycure.moviefan.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.avelycure.moviefan.data.local.type_converters.*
import com.avelycure.moviefan.data.remote.dto.details.MovieGenre
import com.avelycure.moviefan.data.remote.dto.details.ProductionCompanies
import com.avelycure.moviefan.data.remote.dto.details.ProductionCountries
import com.avelycure.moviefan.data.remote.dto.details.SpokenLanguages
import com.avelycure.moviefan.domain.models.MovieInfo

@Entity(tableName = "movies")
data class EntityMovie(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val adult: Boolean = false,
    val budget: Int = 0,
    val imdbId: String? = "",
    val originalLanguage: String = "",
    val originalTitle: String = "",
    val overview: String = "",
    val popularity: Float = 0F,
    @TypeConverters(ConverterGenre::class)
    val genres: List<MovieGenre> = emptyList(),
    @TypeConverters(ConverterProductionCompanies::class)
    val productionCompanies: List<ProductionCompanies> = emptyList(),
    @TypeConverters(ConverterProductionCountries::class)
    val productionCountries: List<ProductionCountries> = emptyList(),
    val releaseDate: String = "",
    @TypeConverters(ConverterLanguages::class)
    val spokenLanguages: List<SpokenLanguages> = emptyList(),
    val status: String = "",
    val revenue: Int = 0,
    val tagline: String? = "",
    val title: String = "",
    val voteAverage: Float = 0F,
    val voteCount: Int = 0,
    val posterPath: String? = "",
    @TypeConverters(ConverterCast::class)
    val cast: List<String> = emptyList(),
    val movieId: Int
)

fun EntityMovie.toMovieInfo():MovieInfo{
    return MovieInfo(
        adult = adult,
        budget = budget,
        imdbId = imdbId,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        overview = overview,
        popularity = popularity,
        genres = genres,
        productionCompanies = productionCompanies,
        productionCountries = productionCountries,
        releaseDate = releaseDate,
        spokenLanguages = spokenLanguages,
        status = status,
        revenue = revenue,
        tagline = tagline,
        voteAverage = voteAverage,
        title = title,
        voteCount = voteCount,
        posterPath = posterPath,
        cast = cast,
        movieId = movieId
    )
}
