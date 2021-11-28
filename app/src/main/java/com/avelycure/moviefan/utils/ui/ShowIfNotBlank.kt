package com.avelycure.moviefan.utils.ui

import android.view.View
import androidx.appcompat.widget.AppCompatTextView

fun showIfNotBlank(view: AppCompatTextView, viewTitle: AppCompatTextView, value: String?) {
    if (value != null) {
        if (value.isBlank()) {
            view.visibility = View.GONE
            viewTitle.visibility = View.GONE
        } else {
            view.visibility = View.VISIBLE
            viewTitle.visibility = View.VISIBLE
            view.text = value
        }
    }
}