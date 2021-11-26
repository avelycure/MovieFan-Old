package com.avelycure.moviefan.presentation.home

import android.os.Parcelable
import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.avelycure.moviefan.domain.interactors.GetPopularMovies
import com.avelycure.moviefan.domain.interactors.SearchMovie
import com.avelycure.moviefan.domain.models.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
@Inject constructor(
    private val getPopularMovies: GetPopularMovies,
    private val searchMovie: SearchMovie
) : ViewModel() {
    private var mPagingData: Flow<PagingData<Movie>>? = null

    fun getPopularMovies() = getPopularMovies
        .execute()

    fun searchMovie(query: String) = searchMovie
        .execute(query)

    fun lookForPopularMovies(): Flow<PagingData<Movie>> {
        if (mPagingData != null) return mPagingData as Flow<PagingData<Movie>>
        else{
            mPagingData = getPopularMovies.execute()
            return mPagingData as Flow<PagingData<Movie>>
        }
    }
}