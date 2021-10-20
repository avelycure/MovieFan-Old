package com.avelycure.moviefan.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.avelycure.moviefan.data.remote.PostsService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel
@Inject constructor(
    private val postsService: PostsService
) : ViewModel() {

    fun getPhotos() {
        viewModelScope.launch {
            val request = postsService.getPosts().results.map{

            }

            Log.d("mytag", "Network request: ${1}")
        }
    }
}