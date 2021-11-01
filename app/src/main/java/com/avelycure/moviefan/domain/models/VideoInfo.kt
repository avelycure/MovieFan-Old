package com.avelycure.moviefan.domain.models

import com.avelycure.moviefan.common.Constants

/**
 * Class to store information about movie. Now it stores only key, but class is needed for easier
 * development in future
 */
data class VideoInfo(
    val key: String = Constants.NO_TRAILER_CODE.toString()
)
