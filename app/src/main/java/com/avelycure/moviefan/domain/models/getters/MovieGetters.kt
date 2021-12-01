package com.avelycure.moviefan.domain.models.getters

import com.avelycure.moviefan.domain.models.Movie

fun Movie.getOriginalTitleAndReleaseDate(): String =
    if (this.releaseDate.isNotEmpty())
        originalTitle + ", " + this.releaseDate.substring(0, 4)
    else
        originalTitle