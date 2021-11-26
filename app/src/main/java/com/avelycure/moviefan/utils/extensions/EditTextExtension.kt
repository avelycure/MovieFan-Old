package com.avelycure.moviefan.utils.extensions

import android.widget.EditText
import androidx.core.widget.doOnTextChanged
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

fun EditText.getQueryChangeStateFlow(): StateFlow<String> {
    val query = MutableStateFlow("")
    doOnTextChanged { text, start, before, count ->
        query.value = text.toString()
    }
    return query
}