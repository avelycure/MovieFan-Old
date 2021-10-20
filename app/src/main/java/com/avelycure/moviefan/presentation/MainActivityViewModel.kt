package com.avelycure.moviefan.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.avelycure.moviefan.data.remote.MovieRepository
import com.avelycure.moviefan.data.remote.PostsService
import com.avelycure.moviefan.data.remote.dto.toPopularMovie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel
@Inject constructor(
    val repository: MovieRepository
) : ViewModel() {

    fun getPhotos() {
        viewModelScope.launch {

        }
    }
}