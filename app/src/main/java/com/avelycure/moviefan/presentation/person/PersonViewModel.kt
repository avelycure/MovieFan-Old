package com.avelycure.moviefan.presentation.person

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.avelycure.moviefan.domain.interactors.GetPerson
import com.avelycure.moviefan.domain.models.Person
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class PersonViewModel
@Inject constructor(
    private val getPerson: GetPerson
) : ViewModel() {

    @FlowPreview
    @ExperimentalCoroutinesApi
    fun searchPerson(queryFlow: StateFlow<String>): Flow<PagingData<Person>> {
        return queryFlow.debounce(500)
            .filter { query ->
                return@filter query.isNotEmpty()
            }
            .distinctUntilChanged()
            .flatMapLatest { query ->
                getPerson.execute(query)
            }
    }
}