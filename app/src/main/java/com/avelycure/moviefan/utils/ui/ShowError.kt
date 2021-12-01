package com.avelycure.moviefan.utils.ui

import android.content.Context
import android.graphics.Color
import android.view.View
import android.widget.TextView
import com.avelycure.moviefan.R
import com.google.android.material.snackbar.Snackbar

/**
 * Function to show snackbar
 */
fun showError(view: View, context: Context, text: String) {
    val sb = Snackbar.make(
        context,
        view,
        text,
        Snackbar.LENGTH_SHORT
    )
    (sb.view as Snackbar.SnackbarLayout).findViewById<TextView>(R.id.snackbar_text)
        .setTextColor(Color.WHITE)
    (sb.view as Snackbar.SnackbarLayout).setBackgroundColor(context.resources.getColor(R.color.alazar_red))
    sb.show()
}