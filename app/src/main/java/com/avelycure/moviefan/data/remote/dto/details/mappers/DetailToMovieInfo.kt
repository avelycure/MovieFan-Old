package com.avelycure.moviefan.data.remote.dto.details.mappers

import com.avelycure.moviefan.data.remote.dto.details.DetailResponse
import com.avelycure.moviefan.domain.models.MovieInfo

fun DetailResponse.toMovieInfo(): MovieInfo {
    return MovieInfo(
        adult = adult,
        budget = budget,
        imdbId = imdb_id,
        originalLanguage = original_language,
        originalTitle = original_title,
        overview = overview,
        popularity = popularity,
        genres = genres,
        productionCompanies = production_companies,
        productionCountries = production_countries,
        releaseDate = release_date,
        spokenLanguages = spoken_languages,
        status = status,
        revenue = revenue,
        tagline = tagline,
        voteAverage = vote_average / 2F,
        title = title,
        voteCount = vote_count,
        posterPath = poster_path,
        cast = credits.cast.map {
            it.name
        }
    )
}