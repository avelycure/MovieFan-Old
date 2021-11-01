package com.avelycure.moviefan.utils

import android.content.Context
import android.graphics.Color
import android.view.View
import android.widget.TextView
import androidx.core.content.ContentProviderCompat.requireContext
import com.avelycure.moviefan.R
import com.avelycure.moviefan.common.Constants
import com.google.android.material.snackbar.Snackbar

fun showNoInternetConnectionError(view: View, context: Context, text: String) {
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