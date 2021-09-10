package com.rafag.stonks.android.search.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rafag.stonks.android.search.domain.SearchStonksUseCase
import com.rafag.stonks.android.search.domain.StonkSearch
import com.rafag.stonks.android.search.domain.ToggleFavouriteUseCase
import com.rafag.stonks.android.search.view.SearchStonkUiItem
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SearchViewModel(
    private val searchUseCase: SearchStonksUseCase,
    private val toggleFavouriteUseCase: ToggleFavouriteUseCase,
    ) : ViewModel() {

    val state: MutableLiveData<SearchState> = MutableLiveData()

    fun search(query: String) {
        viewModelScope.launch {
            searchUseCase.search(query).collect { searchStonks ->
                state.value = SearchState(
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

data class SearchState(
    val searchStonks: List<SearchStonkUiItem>
)