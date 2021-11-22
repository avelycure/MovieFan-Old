package com.avelycure.moviefan.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.avelycure.moviefan.data.remote.dto.details.MovieGenre
import com.avelycure.moviefan.data.remote.dto.details.ProductionCompanies
import com.avelycure.moviefan.data.remote.dto.details.ProductionCountries
import com.avelycure.moviefan.data.remote.dto.details.SpokenLanguages

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
    val genres: List<MovieGenre> = emptyList(),
    val productionCompanies: List<ProductionCompanies> = emptyList(),
    val productionCountries: List<ProductionCountries> = emptyList(),
    val releaseDate: String = "",
    val spokenLanguages: List<SpokenLanguages> = emptyList(),
    val status: String = "",
    val revenue: Int = 0,
    val tagline: String? = "",
    val title: String = "",
    val voteAverage: Float = 0F,
    val voteCount: Int = 0,
    val posterPath: String? = "",
    val cast: List<String> = emptyList()
)
