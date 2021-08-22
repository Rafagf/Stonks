package com.rafag.stonks.android.search.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rafag.stonks.android.search.view.StonkItemState
import com.rafag.stonks.data.search.SearchRepository
import kotlinx.coroutines.launch

class SearchViewModel(private val searchRepository: SearchRepository) : ViewModel() {

    val state: MutableLiveData<SearchState> = MutableLiveData()

    fun search(query: String) {
        viewModelScope.launch {
            // todo change to proper mapping, only quick hacking here - also,
            // Search values are completely different to StonkItemState,
            // maybe we don't need to show the price here!
            val search = searchRepository.searchRequest(query)
            val filteredList = search.list.map {
                StonkItemState(name = it.displaySymbol, price = "foo", "faa")
            }
            state.value = SearchState(filteredList)
        }
    }
}

data class SearchState(
    val stonks: List<StonkItemState>
)