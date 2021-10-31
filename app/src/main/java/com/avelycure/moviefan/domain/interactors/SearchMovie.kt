package com.avelycure.moviefan.domain.interactors

import com.avelycure.moviefan.data.remote.repository.MovieRepository

class SearchMovie(
    val repository: MovieRepository
) {
    fun execute(query: String) = repository
        .getSearchPager(query)
        .flow
}