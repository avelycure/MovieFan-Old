package com.avelycure.moviefan.presentation.movie_info

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.avelycure.moviefan.domain.interactors.GetDetails
import com.avelycure.moviefan.domain.interactors.GetVideos
import com.avelycure.moviefan.domain.models.MovieInfo
import com.avelycure.moviefan.domain.models.VideoInfo
import com.avelycure.moviefan.domain.state.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieInfoViewModel
@Inject constructor(
    val getDetails: GetDetails,
    val getVideos: GetVideos
) : ViewModel() {
    val state: MutableStateFlow<MovieInfoState> = MutableStateFlow(MovieInfoState())

    fun getVideos(id: Int) {
        viewModelScope.launch {
            getVideos
                .execute(id)
                .collectLatest { dataState ->
                    when (dataState) {
                        is DataState.Data -> {
                            state.value =
                                state.value.copy(videoInfo = dataState.data ?: VideoInfo())
                        }
                        is DataState.Loading -> {
                            state.value =
                                state.value.copy(videoLoadingState = dataState.progressBarState)
                        }
                        is DataState.Response -> {
                        }
                    }
                }
        }
    }

    fun getDetails(id: Int) {
        viewModelScope.launch {
            getDetails
                .execute(id)
                .collectLatest { dataState ->
                    when (dataState) {
                        is DataState.Data -> {
                            state.value =
                                state.value.copy(movieInfo = dataState.data ?: MovieInfo())
                        }
                        is DataState.Loading -> {
                            state.value =
                                state.value.copy(detailsLoadingState = dataState.progressBarState)
                        }
                        is DataState.Response -> {

                        }
                    }
                }
        }
    }
}