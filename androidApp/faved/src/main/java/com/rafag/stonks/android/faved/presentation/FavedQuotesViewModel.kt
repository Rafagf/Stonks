package com.rafag.stonks.android.faved.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rafag.stonks.domain.usecases.FavedQuote
import com.rafag.stonks.domain.usecases.FetchFavedQuotesUseCase
import com.rafag.stonks.domain.usecases.ToggleFavouriteUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class FavedViewModel(
    private val fetchFavedQuotesUseCase: FetchFavedQuotesUseCase,
    private val toggleFavouriteUseCase: ToggleFavouriteUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow<FavedState>(FavedState.Loading)
    val state: StateFlow<FavedState> get() = _state

    fun load() {
        viewModelScope.launch {
            fetchFavedQuotesUseCase.invoke().collect { favedQuotes ->
                _state.value = FavedState.Content(
                    quotes = favedQuotes.map {
                        it.toFavedQuoteUi()
                    }
                )
            }
        }
    }

    fun onDeleteStonkClicked(item: FavedQuoteUi) {
        viewModelScope.launch {
            toggleFavouriteUseCase.unsaved(item.symbol)
        }
    }
}

private fun FavedQuote.toFavedQuoteUi() = FavedQuoteUi(
    symbol = symbol,
    current = current.roundTo(2).toString(),
    change = "${open.roundTo(2)} (${formatPercentageChange(percentageChange(open, current))})",
    isUp = percentageChange(open, current) > 0
)

private fun formatPercentageChange(percentageChange: Double): String {
    val rounded = percentageChange.roundTo(2)
    return if (rounded > 0) "+$rounded" else rounded.toString()
}

private fun percentageChange(initial: Double, final: Double) = ((final - initial) / initial) * 100

fun Double.roundTo(n: Int): Double {
    return "%.${n}f".format(this).toDouble()
}

data class FavedQuoteUi(
    val symbol: String,
    val current: String,
    val change: String,
    val isUp: Boolean
)

sealed class FavedState {
    object Loading : FavedState()
    object Error : FavedState()
    data class Content(val quotes: List<FavedQuoteUi>) : FavedState()
}
