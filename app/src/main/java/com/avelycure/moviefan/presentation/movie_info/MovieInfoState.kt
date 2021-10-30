package com.avelycure.moviefan.presentation.movie_info

import com.avelycure.moviefan.domain.models.MovieInfo
import com.avelycure.moviefan.domain.models.VideoInfo
import com.avelycure.moviefan.domain.state.ProgressBarState
import com.avelycure.moviefan.domain.state.Queue
import com.avelycure.moviefan.domain.state.UIComponent

data class MovieInfoState(
    val progressBarState: ProgressBarState = ProgressBarState.Idle,
    val errorQueue: Queue<UIComponent> = Queue(mutableListOf()),
    val movieInfo: MovieInfo = MovieInfo(),
    val videoInfo: VideoInfo = VideoInfo()
)