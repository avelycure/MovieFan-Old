package com.avelycure.moviefan.presentation.search_film

import androidx.lifecycle.ViewModel
import com.avelycure.moviefan.domain.interactors.SearchMovie
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchMovieViewModel
    @Inject constructor(
        val searchMovie: SearchMovie
    ): ViewModel() {


}