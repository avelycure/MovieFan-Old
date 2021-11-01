package com.avelycure.moviefan.presentation.movie_info

/**
 * Events that could happen in Movie Info fragment
 */
sealed class MovieInfoEvents {

    object OnRemoveHeadFromQueue: MovieInfoEvents()

    data class OnOpenInfoFragment(
        val movieId: Int
    ): MovieInfoEvents()
}