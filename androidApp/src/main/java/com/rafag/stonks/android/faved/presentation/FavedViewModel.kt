package com.rafag.stonks.android.faved.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FavedViewModel() : ViewModel() {

    private val _state = MutableStateFlow<FavedState>(FavedState.Loading)
    val stateFlow: StateFlow<FavedState> get() = _state

    fun load() {
        viewModelScope.launch {
            _state.value = FavedState.Content(listOf("1,3,5,7"))
        }
    }

    fun foo() {
        _state.value = FavedState.Content(listOf("2,4,6,8"))
    }
}

sealed class FavedState {
    object Loading : FavedState()
    object Error : FavedState()
    data class Content(val strings: List<String>) : FavedState()
}
