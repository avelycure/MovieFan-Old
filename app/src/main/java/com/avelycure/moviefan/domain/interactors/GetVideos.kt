package com.avelycure.moviefan.domain.interactors

import com.avelycure.moviefan.data.remote.MovieRepository
import com.avelycure.moviefan.domain.models.MovieInfo
import com.avelycure.moviefan.domain.models.VideoInfo
import com.avelycure.moviefan.domain.state.DataState
import com.avelycure.moviefan.domain.state.ProgressBarState
import com.avelycure.moviefan.domain.state.UIComponent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetVideos(
    private val repository: MovieRepository
) {
    fun execute(id: Int): Flow<DataState<VideoInfo>> = flow {
        try {
            emit(DataState.Loading(progressBarState = ProgressBarState.Loading))
            val result = repository.getVideos(id)
            emit(DataState.Data(result))
        } catch (e: Exception) {
            emit(
                DataState.Response<VideoInfo>(
                    uiComponent = UIComponent.Dialog(
                        title = "Error",
                        description = e.message ?: "Unknown error occured"
                    )
                )
            )
        } finally {
            emit(DataState.Loading(progressBarState = ProgressBarState.Idle))
        }
    }

}