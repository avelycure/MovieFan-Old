package com.avelycure.moviefan.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.avelycure.moviefan.data.local.type_converters.*
import com.avelycure.moviefan.data.remote.dto.details.MovieGenre
import com.avelycure.moviefan.data.remote.dto.details.ProductionCompanies
import com.avelycure.moviefan.data.remote.dto.details.ProductionCountries
import com.avelycure.moviefan.data.remote.dto.details.SpokenLanguages

@Entity(tableName = "movies_info")
data class EntityMovieInfo(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val adult: Boolean = false,
    val budget: Int = 0,
    val imdbId: String? = "",
    val originalLanguage: String = "",
    val originalTitle: String = "",
    val overview: String = "",
    val popularity: Float = 0F,
    @TypeConverters(ConverterListMovieGenre::class)
    val genres: List<MovieGenre> = emptyList(),
    @TypeConverters(ConverterListProductionCompanies::class)
    val productionCompanies: List<ProductionCompanies> = emptyList(),
    @TypeConverters(ConverterListProductionCountries::class)
    val productionCountries: List<ProductionCountries> = emptyList(),
    val releaseDate: String = "",
    @TypeConverters(ConverterListSpokenLanguages::class)
    val spokenLanguages: List<SpokenLanguages> = emptyList(),
    val status: String = "",
    val revenue: Int = 0,
    val tagline: String? = "",
    val title: String = "",
    val voteAverage: Float = 0F,
    val voteCount: Int = 0,
    val posterPath: String? = "",
    @TypeConverters(ConverterListString::class)
    val cast: List<String> = emptyList(),
    val movieId: Int,
    @TypeConverters(ConverterListString::class)
    val imagesBackdrop: List<String> = emptyList(),
    @TypeConverters(ConverterListString::class)
    val imagesPosters: List<String> = emptyList()
)