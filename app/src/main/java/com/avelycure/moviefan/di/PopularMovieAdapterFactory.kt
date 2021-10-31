package com.avelycure.moviefan.di

import com.avelycure.moviefan.data.remote.adapters.MovieAdapter
import com.avelycure.moviefan.domain.models.Movie
import dagger.assisted.AssistedFactory

@AssistedFactory
interface PopularMovieAdapterFactory {
    fun create(onClickItem: (Movie) -> Unit): MovieAdapter
}