package com.avelycure.moviefan.common

import com.avelycure.moviefan.BuildConfig

/**
 * Constants for creating requests to tmdb
 */
object RequestConstants {
    const val BASE_URL = "https://api.themoviedb.org/3"

    const val YOUTUBE_API_KEY = BuildConfig.YOUTUBE_API_KEY
    const val API_KEY = BuildConfig.TMDB_API_KEY

    const val POPULAR_MOVIES = "movie/popular"
    const val IMAGE = "https://image.tmdb.org/t/p/w500"

    const val PERSON_IMAGES = "images"
    const val CREDITS = "credits"
    const val MOVIE_IMAGES = "images"
    const val SIMILAR_MOVIES = "similar"
}