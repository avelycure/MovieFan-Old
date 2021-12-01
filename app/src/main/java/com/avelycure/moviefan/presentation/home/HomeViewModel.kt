package com.avelycure.moviefan.presentation.home

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.avelycure.moviefan.domain.interactors.home.GetPopularMovies
import com.avelycure.moviefan.domain.interactors.home.FindMovie
import com.avelycure.moviefan.domain.models.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
@Inject constructor(
    private val getPopularMovies: GetPopularMovies,
    private val findMovie: FindMovie
) : ViewModel() {
    private var mPagingData: Flow<PagingData<Movie>>? = null

    fun getPopularMovies() = getPopularMovies
        .execute()

    @FlowPreview
    @ExperimentalCoroutinesApi
    fun searchMovie(queryFlow: Flow<String>): Flow<PagingData<Movie>> {
        return queryFlow
            .debounce(500)
            .filter { query ->
                return@filter query.isNotEmpty()
            }
            .distinctUntilChanged()
            .flatMapLatest { query ->
                findMovie.execute(query)
            }
    }

    fun lookForPopularMovies(): Flow<PagingData<Movie>> {
        if (mPagingData != null) return mPagingData as Flow<PagingData<Movie>>
        else {
            mPagingData = getPopularMovies.execute()
            return mPagingData as Flow<PagingData<Movie>>
        }
    }
}