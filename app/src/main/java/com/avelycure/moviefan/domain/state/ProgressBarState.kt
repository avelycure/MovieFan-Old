package com.avelycure.moviefan.domain.state

sealed class ProgressBarState {

    object Loading : ProgressBarState()

    object Idle : ProgressBarState()
}
