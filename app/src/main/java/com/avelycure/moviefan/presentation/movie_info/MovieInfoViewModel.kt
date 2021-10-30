package com.avelycure.moviefan.presentation.movie_info

import androidx.lifecycle.ViewModel
import com.avelycure.moviefan.data.remote.MovieRepository
import com.avelycure.moviefan.domain.interactors.GetDetails
import com.avelycure.moviefan.domain.models.MovieInfo
import com.avelycure.moviefan.domain.models.VideoInfo
import com.avelycure.moviefan.domain.state.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class MovieInfoViewModel
@Inject constructor(
    val repository: MovieRepository,
    val getDetails: GetDetails
) : ViewModel() {

    fun getVideos(id: Int): Flow<VideoInfo> = flow {
        emit(repository.getVideos(id))
    }

    fun getDetails(id: Int): Flow<DataState<MovieInfo>> = getDetails.execute(id)
}