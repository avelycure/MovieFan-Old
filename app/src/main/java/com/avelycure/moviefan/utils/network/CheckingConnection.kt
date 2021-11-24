package com.avelycure.moviefan.utils

import android.content.Context
import android.net.ConnectivityManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity

/**
 * Function to check internet connection
 */
fun isOnline(activity: AppCompatActivity?): Boolean =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        (activity?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).activeNetwork != null
    else
        true