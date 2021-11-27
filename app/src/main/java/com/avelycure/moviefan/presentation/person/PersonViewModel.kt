package com.avelycure.moviefan.presentation.person

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.avelycure.moviefan.domain.interactors.GetPerson
import com.avelycure.moviefan.domain.interactors.GetPersonInfo
import com.avelycure.moviefan.domain.interactors.GetPopularPersons
import com.avelycure.moviefan.domain.models.Person
import com.avelycure.moviefan.domain.models.PersonInfo
import com.avelycure.moviefan.domain.state.DataState
import com.avelycure.moviefan.domain.state.Queue
import com.avelycure.moviefan.domain.state.UIComponent
import com.avelycure.moviefan.presentation.movie_info.MovieInfoState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class PersonViewModel
@Inject constructor(
    private val getPerson: GetPerson,
    private val getPersonInfo: GetPersonInfo,
    private val getPopularPersons: GetPopularPersons
) : ViewModel() {
    private val _state = MutableStateFlow(PersonState())
    val state = _state.asStateFlow()

    fun onExpand(id: Int): Flow<PersonInfo> =
        getPersonInfo.execute(id).map { dataState ->
            when (dataState) {
                is DataState.Data -> {
                    dataState.data ?: PersonInfo()
                }
                is DataState.Response -> {
                    appendToMessageQueue(
                        dataState.uiComponent as UIComponent.Dialog
                    )
                    PersonInfo()
                }
                is DataState.Loading -> {
                    PersonInfo()
                }
            }
        }

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

    fun getPopularPersons() = getPopularPersons.execute()

    private fun appendToMessageQueue(uiComponent: UIComponent) {
        val queue: Queue<UIComponent> = Queue(mutableListOf())
        for (i in 0 until _state.value.errorQueue.count())
            _state.value.errorQueue.poll()?.let { queue.add(it) }
        queue.add(uiComponent)
        _state.value = _state.value.copy(errorQueue = queue)
    }

    private fun removeHeadMessage() {
        try {
            val queue = _state.value.errorQueue
            queue.remove()
            _state.value = _state.value.copy(errorQueue = queue)
        } catch (e: Exception) {
            Log.d("mytag", "Nothing to remove from MessageQueue")
        }
    }
}