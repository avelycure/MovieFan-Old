package com.avelycure.moviefan.domain.interactors.person

import com.avelycure.moviefan.data.remote.mappers.toPersonInfo
import com.avelycure.moviefan.data.repository.MovieRepository
import com.avelycure.moviefan.domain.models.PersonInfo
import com.avelycure.moviefan.domain.state.DataState
import com.avelycure.moviefan.domain.state.ProgressBarState
import com.avelycure.moviefan.domain.state.UIComponent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetPersonInfo(
    val repository: MovieRepository
) {
    fun execute(id: Int): Flow<DataState<PersonInfo>> = flow {
        try {
            emit(DataState.Loading(progressBarState = ProgressBarState.Loading))
            val result = repository.getPersonInfo(id)
            emit(DataState.Data(result.toPersonInfo()))
        } catch (e: Exception) {
            emit(
                DataState.Response<PersonInfo>(
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