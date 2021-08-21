package com.rafag.stonks.android

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rafag.stonks.android.views.StonkItemState
import com.rafag.stonks.android.views.dummyData
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {

    val state: MutableLiveData<SearchState> = MutableLiveData()

    fun search(query: String) {
        viewModelScope.launch {
            val filtered = dummyData.filter {
                it.name.toLowerCase().startsWith(query.toLowerCase())
            }
            state.value = SearchState(filtered)
        }
    }
}

data class SearchState(
    val stonks: List<StonkItemState>
)