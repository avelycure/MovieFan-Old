package com.avelycure.moviefan.di.modules

import com.avelycure.moviefan.data.remote.PopularMovieAdapter
import com.avelycure.moviefan.domain.models.PopularMovie
import dagger.assisted.AssistedFactory

@AssistedFactory
interface PopularMovieAdapterFactory {
    fun create(onClickItem: (PopularMovie) -> Unit): PopularMovieAdapter
}