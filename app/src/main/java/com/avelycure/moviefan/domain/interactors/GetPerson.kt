package com.avelycure.moviefan.domain.interactors

import androidx.paging.map
import com.avelycure.moviefan.data.remote.mappers.toPerson
import com.avelycure.moviefan.data.repository.MovieRepository
import kotlinx.coroutines.flow.map

class GetPerson(
    val repository: MovieRepository
) {
    fun execute(query: String) = repository
        .getSearchPersonPager(query)
        .flow.map { pagingData ->
            pagingData.map {
                it.toPerson()
            }
        }
}