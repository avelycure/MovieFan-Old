package com.avelycure.moviefan.di

import com.avelycure.moviefan.data.remote.adapters.PopularMovieAdapter
import com.avelycure.moviefan.domain.models.PopularMovie
import dagger.assisted.AssistedFactory

@AssistedFactory
interface PopularMovieAdapterFactory {
    fun create(onClickItem: (PopularMovie) -> Unit): PopularMovieAdapter
}