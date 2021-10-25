package com.avelycure.moviefan.common

object Constants {
    const val MOVIE_TITLE = "movie_title"
    const val API_KEY = "f6e8d9a1b105298e3c45b42c87529f29"

    const val BASE_URL = "https://api.themoviedb.org/3/movie"

    const val POPULAR_MOVIES = "$BASE_URL/popular?api_key=$API_KEY&page="

    const val IMAGE = "https://image.tmdb.org/t/p/w500"

    const val YOUTUBE_API_KEY = "AIzaSyBPAAvMt6j3YtG1FsK7wmUso0L4wYhk-Zk"
    const val VIDEO_PATH_KEY = "video_path"

    //todo change
    const val NO_TRAILER_CODE = -1

    const val ID_KEY = "movie"

    const val CREDITS = "&append_to_response=credits"

    val movieGenre = mapOf(
        28 to "Action",
        12 to "Adventure",
        16 to "Animation",
        35 to "Comedy",
        80 to "Crime",
        99 to "Documentary",
        18 to "Drama",
        10751 to "Family",
        14 to "Fantasy",
        36 to "History",
        27 to "Horror",
        10402 to "Music",
        9648 to "Mystery",
        10749 to "Romance",
        878 to "Science Fiction",
        10770 to "TV Movie",
        53 to "Thriller",
        10752 to "War",
        37 to "Western"
    )
}