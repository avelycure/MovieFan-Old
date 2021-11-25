package com.avelycure.moviefan.presentation.movie_info

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.avelycure.moviefan.domain.interactors.GetDetails
import com.avelycure.moviefan.domain.interactors.GetTrailerCode
import com.avelycure.moviefan.domain.interactors.SaveToCache
import com.avelycure.moviefan.domain.models.MovieInfo
import com.avelycure.moviefan.domain.models.VideoInfo
import com.avelycure.moviefan.domain.state.DataState
import com.avelycure.moviefan.domain.state.Queue
import com.avelycure.moviefan.domain.state.UIComponent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieInfoViewModel
@Inject constructor(
    private val getDetails: GetDetails,
    private val getTrailerCode: GetTrailerCode,
    private val saveToCache: SaveToCache
) : ViewModel() {
    private val _state = MutableStateFlow(MovieInfoState())
    val state = _state.asStateFlow()

    fun onTrigger(event: MovieInfoEvents) {
        when (event) {
            is MovieInfoEvents.OnRemoveHeadFromQueue -> removeHeadMessage()
            is MovieInfoEvents.OnOpenInfoFragment -> {
                getDetails(event.movieId)
                getTrailerCode(event.movieId)
            }
        }
    }

    private fun getTrailerCode(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            getTrailerCode
                .execute(id)
                .collectLatest { dataState ->
                    when (dataState) {
                        is DataState.Data ->
                            _state.value =
                                _state.value.copy(
                                    videoInfo = dataState.data ?: VideoInfo(),
                                    videoIsAvailable = true
                                )
                        is DataState.Loading -> {
                        }
                        is DataState.Response -> {
                            appendToMessageQueue(
                                dataState.uiComponent as UIComponent.Dialog
                            )
                        }

                    }
                }
        }
    }

    private fun getDetails(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            getDetails
                .execute(id)
                .collectLatest { dataState ->
                    when (dataState) {
                        is DataState.Data -> {
                            viewModelScope.launch(Dispatchers.IO) {
                                saveToCache.execute(movieInfo = dataState.data!!)
                            }
                            _state.value =
                                _state.value.copy(
                                    movieInfo = dataState.data ?: MovieInfo(),
                                    images = dataState.data?.imagesBackdrop ?: emptyList()
                                )
                        }
                        is DataState.Loading ->
                            _state.value =
                                _state.value.copy(detailsLoadingState = dataState.progressBarState)
                        is DataState.Response ->
                            appendToMessageQueue(
                                dataState.uiComponent as UIComponent.Dialog
                            )

                    }
                }
        }
    }

    /**
     * Simple implementation, but problem is that queues are compared with links, so
     * we must create new queue. Then state will be able to differ them
     */
    private fun appendToMessageQueue(uiComponent: UIComponent) {
        val queue: Queue<UIComponent> = Queue(mutableListOf())
        for (i in 0 until _state.value.errorQueue.count())
            _state.value.errorQueue.poll()?.let { queue.add(it) }
        queue.add(uiComponent)
        _state.value = _state.value.copy(errorQueue = queue)
    }

    private fun removeHeadMessage() {
        try {
            val queue = _state.value.errorQueue
            queue.remove()
            _state.value = _state.value.copy(errorQueue = queue)
        } catch (e: Exception) {
            Log.d("mytag", "Nothing to remove from MessageQueue")
        }
    }
}