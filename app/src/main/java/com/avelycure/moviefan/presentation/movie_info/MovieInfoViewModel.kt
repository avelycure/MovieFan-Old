package com.avelycure.moviefan.presentation.movie_info

import androidx.lifecycle.ViewModel
import com.avelycure.moviefan.data.remote.MovieRepository
import com.avelycure.moviefan.domain.models.MovieInfo
import com.avelycure.moviefan.domain.models.VideoInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class MovieInfoViewModel
@Inject constructor(
    val repository: MovieRepository
) : ViewModel() {

    fun getVideos(id: Int): Flow<VideoInfo> = flow {
        emit(repository.getVideos(id))
    }

    fun getDetails(id: Int): Flow<MovieInfo> = flow {
        emit(repository.getDetails(id))
    }
}