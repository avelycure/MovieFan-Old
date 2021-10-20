package com.avelycure.moviefan.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.avelycure.moviefan.data.remote.MovieRepository
import com.avelycure.moviefan.domain.PopularMovie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel
@Inject constructor(
    val repository: MovieRepository
) : ViewModel() {

    fun getPhotos(): Flow<PagingData<PopularMovie>> {
        return repository.letMovieFlow()
            .cachedIn(viewModelScope)
    }
}