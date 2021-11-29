package com.avelycure.moviefan.common

import com.avelycure.moviefan.BuildConfig


object RequestConstants {
    const val BASE_URL = "https://api.themoviedb.org/3"

    const val YOUTUBE_API_KEY = BuildConfig.YOUTUBE_API_KEY
    const val API_KEY = "?api_key=${BuildConfig.TMDB_API_KEY}"

    const val POPULAR_MOVIES = "movie/popular"
    const val IMAGE = "https://image.tmdb.org/t/p/w500"

    const val VIDEO_PATH_KEY = "video_path"
    const val PERSON_IMAGES = "append_to_response=images"
    const val CREDITS = "append_to_response=credits"
    const val MOVIE_IMAGES = "images"
    const val SIMILAR_MOVIES = "similar"
}