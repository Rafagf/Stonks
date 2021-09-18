package com.rafag.stonks.android.search.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rafag.stonks.android.search.domain.SearchStonksUseCase
import com.rafag.stonks.android.search.domain.StonkSearch
import com.rafag.stonks.android.search.domain.ToggleFavouriteUseCase
import com.rafag.stonks.android.search.view.SearchStonkUiItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SearchViewModel(
    private val searchUseCase: SearchStonksUseCase,
    private val toggleFavouriteUseCase: ToggleFavouriteUseCase,
    ) : ViewModel() {

    private val _state = MutableStateFlow<SearchState>(SearchState.Loading)
    val state: StateFlow<SearchState> get() = _state

    fun search(query: String) {
        viewModelScope.launch {
            searchUseCase.search(query).collect { searchStonks ->
                _state.value = SearchState.Content(
                    searchStonks = searchStonks.map {
                        it.toSearchStonkUiItem()
                    }
                )
            }
        }
    }

    fun onStonkFaved(item: SearchStonkUiItem) {
        viewModelScope.launch {
            toggleFavouriteUseCase.saved(item.symbol)
        }
    }

    fun onStonkUnfaved(item: SearchStonkUiItem) {
        viewModelScope.launch {
            toggleFavouriteUseCase.unsaved(item.symbol)
        }
    }

    private fun StonkSearch.toSearchStonkUiItem() = SearchStonkUiItem(
        name = name,
        symbol = symbol,
        faved = faved
    )
}

sealed class SearchState {
    object Loading : SearchState()
    object Error : SearchState()
    data class Content(val searchStonks: List<SearchStonkUiItem>) : SearchState()
}
