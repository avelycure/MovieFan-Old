package com.avelycure.moviefan.common

object Constants {
    const val API_KEY = "f6e8d9a1b105298e3c45b42c87529f29"
    private const val BASE_URL = "https://api.themoviedb.org/3"
    const val POPULAR_MOVIES = "$BASE_URL/movie/popular?api_key=$API_KEY&page="
    const val IMAGE = "https://image.tmdb.org/t/p/w500"

    const val YOUTUBE_API_KEY = "AIzaSyBPAAvMt6j3YtG1FsK7wmUso0L4wYhk-Zk"
    const val VIDEO_PATH_KEY = "video_path"
}