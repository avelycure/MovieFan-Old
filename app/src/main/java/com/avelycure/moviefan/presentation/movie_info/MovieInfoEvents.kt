package com.avelycure.moviefan.presentation.movie_info

sealed class MovieInfoEvents {

    object OnRemoveHeadFromQueue: MovieInfoEvents()

    data class OnOpenInfoFragment(
        val movieId: Int
    ): MovieInfoEvents()
}