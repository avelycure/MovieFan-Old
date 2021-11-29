package com.avelycure.moviefan.common

/**
 * Now it is hard coded constants, but later the will be removed with concrete language equivalent
 */
object TemporaryConstants {

    const val POPULAR_MOVIE_TITLE_DEFAULT = "Popular movies"
    const val PERSON_SEARCH_TITLE_DEFAULT = "Search person"
    const val MOVIE_INFO_TITLE_DEFAULT = "Movie info"

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