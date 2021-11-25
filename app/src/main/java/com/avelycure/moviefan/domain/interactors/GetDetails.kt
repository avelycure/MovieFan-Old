package com.avelycure.moviefan.domain.interactors

import com.avelycure.moviefan.data.local.mappers.toMovieInfo
import com.avelycure.moviefan.data.remote.dto.details.mappers.toMovieInfo
import com.avelycure.moviefan.data.repository.MovieRepository
import com.avelycure.moviefan.domain.models.MovieInfo
import com.avelycure.moviefan.domain.state.DataState
import com.avelycure.moviefan.domain.state.ProgressBarState
import com.avelycure.moviefan.domain.state.UIComponent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import io.ktor.utils.io.errors.*

/**
 * Makes request to server and returns detailed information about movie
 */
class GetDetails(
    private val repository: MovieRepository
) {
    fun execute(id: Int): Flow<DataState<MovieInfo>> = flow {
        try {
            emit(DataState.Loading(progressBarState = ProgressBarState.Loading))
            try {
                emit(DataState.Data(repository.getDetails(id).toMovieInfo()))
            } catch (e: IOException) {
                emit(
                    DataState.Response<MovieInfo>(
                        uiComponent = UIComponent.Dialog(
                            description = e.message ?: "No internet connection"
                        )
                    )
                )
                emit(DataState.Data(repository.getMovieInfoFromCache(id).toMovieInfo()))
            }
        } catch (e: Exception) {
            emit(
                DataState.Response<MovieInfo>(
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