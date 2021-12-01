package com.avelycure.moviefan.presentation.person

import com.avelycure.moviefan.domain.state.ProgressBarState
import com.avelycure.moviefan.domain.state.Queue
import com.avelycure.moviefan.domain.state.UIComponent

/**
 * State of PersonFragment
 */
data class PersonState(
    val detailsLoadingState: ProgressBarState = ProgressBarState.Idle,
    val errorQueue: Queue<UIComponent> = Queue(mutableListOf())
)