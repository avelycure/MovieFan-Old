package com.avelycure.moviefan.domain.interactors

import androidx.paging.PagingData
import com.avelycure.moviefan.data.remote.MovieRepository
import com.avelycure.moviefan.domain.models.MovieInfo
import com.avelycure.moviefan.domain.models.PopularMovie
import com.avelycure.moviefan.domain.state.DataState
import com.avelycure.moviefan.domain.state.ProgressBarState
import com.avelycure.moviefan.domain.state.UIComponent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetDetails(
    private val repository: MovieRepository) {

    fun execute(id: Int): Flow<DataState<MovieInfo>> = flow{
        try {
            emit(DataState.Loading(progressBarState = ProgressBarState.Loading))
            val result = repository.getDetails(id)
            emit(DataState.Data(result))
        }catch (e: Exception){
            emit(
                DataState.Response<MovieInfo>(
                    uiComponent = UIComponent.Dialog(
                        title= "Error",
                        description = e.message ?: "Unknown error occured"
                    )
                )
            )
        }finally {
            emit(DataState.Loading(progressBarState = ProgressBarState.Idle))
        }
    }
}