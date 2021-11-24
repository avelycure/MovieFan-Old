package com.avelycure.moviefan.domain.interactors

import androidx.paging.map
import com.avelycure.moviefan.data.remote.mappers.toMovie
import com.avelycure.moviefan.data.repository.MovieRepository
import kotlinx.coroutines.flow.map

/**
 * Makes flow which emits movies
 */
class SearchMovie(
    val repository: MovieRepository
) {
    fun execute(query: String) = repository
        .getSearchPager(query)
        .flow.map {
            pagingData ->
            pagingData.map {
                it.toMovie()
            }
        }
}