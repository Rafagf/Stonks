package com.rafag.stonks.android.faved.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rafag.stonks.android.faved.domain.FavedQuote
import com.rafag.stonks.android.faved.domain.FetchFavedQuotesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class FavedViewModel(
    private val fetchFavedQuotesUseCase: FetchFavedQuotesUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow<FavedState>(FavedState.Loading)
    val state: StateFlow<FavedState> get() = _state

    fun load() {
        viewModelScope.launch {
            fetchFavedQuotesUseCase.invoke().collect { favedStonks ->
                _state.value = FavedState.Content(
                    quotes = favedStonks.map {
                        it.toFavedQuoteUi()
                    }
                )
            }

        }
    }
}

private fun FavedQuote.toFavedQuoteUi() = FavedQuoteUi(
    symbol = symbol,
    current = current,
    open = open,
    percentageChange = percentageChange(open, current).toString()

)

private fun percentageChange(initial: Double, final: Double) = ((final - initial) / initial) * 100

data class FavedQuoteUi(
    val symbol: String,
    val current: Double,
    val open: Double,
    val percentageChange: String
)

sealed class FavedState {
    object Loading : FavedState()
    object Error : FavedState()
    data class Content(val quotes: List<FavedQuoteUi>) : FavedState()
}
