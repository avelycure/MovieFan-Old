package com.avelycure.moviefan.domain.interactors.person

import androidx.paging.map
import com.avelycure.moviefan.data.local.mappers.toPerson
import com.avelycure.moviefan.data.repository.MovieRepository
import kotlinx.coroutines.flow.map

class GetPopularPersons(
    private val repository: MovieRepository
) {
    fun execute() = repository
        .getPagerWithRemoteMediatorForPopularPersons()
        .flow
        .map { pagingData ->
            pagingData.map { entityPopularPerson ->
                entityPopularPerson.toPerson()
            }
        }
}