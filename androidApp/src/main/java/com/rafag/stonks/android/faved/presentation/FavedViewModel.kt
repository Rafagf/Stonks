package com.rafag.stonks.android.faved.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class FavedViewModel() : ViewModel() {

    val state: MutableLiveData<FavedState> = MutableLiveData()

    fun load() {
        viewModelScope.launch {

        }
    }
}

object FavedState