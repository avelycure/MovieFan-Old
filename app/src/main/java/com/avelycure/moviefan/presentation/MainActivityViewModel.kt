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
            val request = postsService.getPosts()

            request.results.map{

            }

            Log.d("mytag", request.results.get(0).original_title)
        }
    }
}