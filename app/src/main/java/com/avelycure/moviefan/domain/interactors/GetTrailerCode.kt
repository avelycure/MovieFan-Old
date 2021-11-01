package com.avelycure.moviefan.domain.interactors

import com.avelycure.moviefan.data.remote.repository.MovieRepository
import com.avelycure.moviefan.domain.models.VideoInfo
import com.avelycure.moviefan.domain.state.DataState
import com.avelycure.moviefan.domain.state.ProgressBarState
import com.avelycure.moviefan.domain.state.UIComponent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetTrailerCode(
    private val repository: MovieRepository
) {
    fun execute(id: Int): Flow<DataState<VideoInfo>> = flow {
        try {
            emit(DataState.Loading(progressBarState = ProgressBarState.Loading))
            val result = repository.getTrailerCode(id)
            emit(DataState.Data(result))
        } catch (e: Exception) {
            emit(
                DataState.Response<VideoInfo>(
                    uiComponent = UIComponent.Dialog(
                        description = e.message ?: "Unknown error occurred"
                    )
                )
            )
        } finally {
            emit(DataState.Loading(progressBarState = ProgressBarState.Idle))
        }
    }

}