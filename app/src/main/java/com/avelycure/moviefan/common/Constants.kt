package com.avelycure.moviefan.common

object Constants {
    const val MOVIE_INFO_TITLE_DEFAULT = "Movie info"
    const val NO_TRAILER_AVAILABLE = "No trailer available for this movie"
    const val POPULAR_MOVIE_TAG = "popular_movie"
    const val POPULAR_MOVIE_TITLE_DEFAULT = "Popular movies"
    const val PERSON_SEARCH_TITLE_DEFAULT = "Search person"
    const val NO_INTERNET_CONNECTION = "No internet connection"
    const val MOVIE_TITLE = "movie_title"
    const val ID_KEY = "movie"

    const val API_KEY = "?api_key=f6e8d9a1b105298e3c45b42c87529f29"
    const val YOUTUBE_API_KEY = "AIzaSyBPAAvMt6j3YtG1FsK7wmUso0L4wYhk-Zk"

    const val BASE_URL = "https://api.themoviedb.org/3"
    const val POPULAR_MOVIES = "movie/popular"
    const val IMAGE = "https://image.tmdb.org/t/p/w500"

    const val VIDEO_PATH_KEY = "video_path"
    const val NO_TRAILER_CODE = -1

    const val CREDITS = "append_to_response=credits"
    const val MOVIE_IMAGES = "images"
    const val SIMILAR_MOVIES = "similar"

    const val PERSON_IMAGES = "append_to_response=images"

    // Response from server contains only genre code, this map is needed to transform
    // numbers to strings
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