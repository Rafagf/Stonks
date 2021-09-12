package com.rafag.stonks.android.faved.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rafag.stonks.android.faved.view.FavedState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FavedViewModel() : ViewModel() {

    private val _stateFlow = MutableStateFlow<FavedState>(FavedState.Loading)
    val stateFlow: StateFlow<FavedState> get() =  _stateFlow

    fun load() {
        viewModelScope.launch {
            _stateFlow.value = FavedState.Content(listOf("1,3,5,7"))
        }
    }

    fun foo() {
        _stateFlow.value = FavedState.Content(listOf("2,4,6,8"))
    }
}
