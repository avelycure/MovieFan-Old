package com.avelycure.moviefan.domain.state

/**
 * Possible states of loading
 */
sealed class ProgressBarState {

    object Loading : ProgressBarState()

    object Idle : ProgressBarState()
}
