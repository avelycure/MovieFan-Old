package com.avelycure.moviefan.domain.state

sealed class UIComponent {
    data class Dialog(
        val description: String,
    ) : UIComponent()
}