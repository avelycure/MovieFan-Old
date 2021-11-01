package com.avelycure.moviefan.domain.state

/**
 * Class with possible reactions on errors or unexpected situations
 */
sealed class UIComponent {
    data class Dialog(
        val description: String,
    ) : UIComponent()
}