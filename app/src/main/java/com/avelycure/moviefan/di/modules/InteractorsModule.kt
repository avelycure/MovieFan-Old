package com.avelycure.moviefan.di.modules

import com.avelycure.moviefan.data.remote.MovieRepository
import com.avelycure.moviefan.domain.interactors.GetDetails
import com.avelycure.moviefan.domain.interactors.GetPopularMovies
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object InteractorsModule {

    @Provides
    fun provideGetPopularMovies(repository: MovieRepository):GetPopularMovies{
        return GetPopularMovies(repository)
    }

    @Provides
    fun provideGetDetails(repository: MovieRepository):GetDetails{
        return GetDetails(repository)
    }
}