package com.avelycure.moviefan.presentation.person

import com.avelycure.moviefan.domain.state.ProgressBarState
import com.avelycure.moviefan.domain.state.Queue
import com.avelycure.moviefan.domain.state.UIComponent

data class PersonState(
    val detailsLoadingState: ProgressBarState = ProgressBarState.Idle,
    val errorQueue: Queue<UIComponent> = Queue(mutableListOf())
)