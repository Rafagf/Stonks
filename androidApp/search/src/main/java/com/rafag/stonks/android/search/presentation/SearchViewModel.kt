package com.rafag.stonks.android.search.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rafag.stonks.domain.usecases.SearchStonksUseCase
import com.rafag.stonks.domain.usecases.StonkSearch
import com.rafag.stonks.domain.usecases.ToggleFavouriteUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch

private const val DEBOUNCE_DELAY = 1000L

class SearchViewModel(
    private val searchUseCase: SearchStonksUseCase,
    private val toggleFavouriteUseCase: ToggleFavouriteUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow<SearchState>(
        SearchState.Content(
            searchStonks = emptyList(),
            searchQuery = MutableStateFlow("")
        )
    )
    val state: StateFlow<SearchState> get() = _state

    init {
        setSearchQueryListener()
    }

    private fun setSearchQueryListener() {
        viewModelScope.launch {
            _state.value.searchQuery
                .debounce(DEBOUNCE_DELAY)
                .distinctUntilChanged()
                .flatMapLatest { query ->
                    _state.value = SearchState.Loading(_state.value.searchQuery)
                    searchUseCase.invoke(query)
                }
                .catch { _state.value = SearchState.Error(_state.value.searchQuery) }
                .collect {
                    _state.value = SearchState.Content(
                        searchStonks = it.toSearchStonkUi(),
                        searchQuery = _state.value.searchQuery
                    )
                }
        }
    }

    fun onStonkFaved(item: SearchStonkUi) {
        viewModelScope.launch {
            toggleFavouriteUseCase.saved(item.symbol)
        }
    }

    fun onStonkUnfaved(item: SearchStonkUi) {
        viewModelScope.launch {
            toggleFavouriteUseCase.unsaved(item.symbol)
        }
    }

    private fun List<StonkSearch>.toSearchStonkUi() = this.map { it.toSearchStonkUiItem() }

    private fun StonkSearch.toSearchStonkUiItem() = SearchStonkUi(
        name = name,
        symbol = symbol,
        faved = faved
    )
}

data class SearchStonkUi(
    val name: String,
    val symbol: String,
    val faved: Boolean,
)

sealed class SearchState(open val searchQuery: MutableStateFlow<String>) {
    data class Loading(override val searchQuery: MutableStateFlow<String>) : SearchState(searchQuery)
    data class Error(override val searchQuery: MutableStateFlow<String>) : SearchState(searchQuery)
    data class Content(
        val searchStonks: List<SearchStonkUi>,
        override val searchQuery: MutableStateFlow<String>
    ) : SearchState(searchQuery)
}
