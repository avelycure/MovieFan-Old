package com.avelycure.moviefan.presentation.movie_info

import androidx.lifecycle.ViewModel
import com.avelycure.moviefan.data.remote.MovieRepository
import com.avelycure.moviefan.domain.interactors.GetDetails
import com.avelycure.moviefan.domain.interactors.GetVideos
import com.avelycure.moviefan.domain.models.MovieInfo
import com.avelycure.moviefan.domain.models.VideoInfo
import com.avelycure.moviefan.domain.state.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class MovieInfoViewModel
@Inject constructor(
    val getDetails: GetDetails,
    val getVideos: GetVideos
) : ViewModel() {

    fun getVideos(id: Int): Flow<DataState<VideoInfo>> = getVideos.execute(id)

    fun getDetails(id: Int): Flow<DataState<MovieInfo>> = getDetails.execute(id)
}