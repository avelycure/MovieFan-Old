package com.avelycure.moviefan.domain.interactors

import com.avelycure.moviefan.data.repository.MovieRepository

/**
 * Makes flow which emits movies
 */
class SearchMovie(
    val repository: MovieRepository
) {
    fun execute(query: String) = repository
        .getSearchPager(query)
        .flow
}