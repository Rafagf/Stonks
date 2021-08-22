package com.rafag.stonks.android.search.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rafag.stonks.android.search.SearchStonksUseCase
import com.rafag.stonks.android.search.StonkSearch
import com.rafag.stonks.android.search.view.SearchStonkItemState
import kotlinx.coroutines.launch

class SearchViewModel(private val searchUseCase: SearchStonksUseCase) : ViewModel() {

    val state: MutableLiveData<SearchState> = MutableLiveData()

    fun search(query: String) {
        viewModelScope.launch {
            state.value = SearchState(searchUseCase.search(query).map { it.toSearchStonkItemState() })
        }
    }

    private fun StonkSearch.toSearchStonkItemState() = SearchStonkItemState(
        name = name,
        symbol = symbol,
        faved = faved
    )
}

data class SearchState(
    val searchStonks: List<SearchStonkItemState>
)