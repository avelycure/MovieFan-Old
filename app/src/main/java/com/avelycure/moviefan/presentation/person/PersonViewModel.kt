package com.avelycure.moviefan.presentation.person

import androidx.lifecycle.ViewModel
import com.avelycure.moviefan.domain.interactors.GetPerson
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PersonViewModel
@Inject constructor(
    private val getPerson: GetPerson
) : ViewModel() {

    fun searchPerson(query: String) = getPerson.execute(query)
}