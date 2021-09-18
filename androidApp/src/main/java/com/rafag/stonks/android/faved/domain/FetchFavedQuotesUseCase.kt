package com.rafag.stonks.android.faved.domain

import com.rafag.stonks.data.favourites.FavouritesRepository
import com.rafag.stonks.data.quote.Quote
import com.rafag.stonks.data.quote.QuoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class FetchFavedQuotesUseCase(
    private val quoteRepository: QuoteRepository,
    private val favouritesRepository: FavouritesRepository
) {
    suspend fun invoke(): Flow<List<FavedQuote>> {
        return favouritesRepository.getAll().flatMapConcat {
            quotes(it).map {
                it.map {
                    FavedQuote(it.symbol, it.current, it.open)
                }
            }
        }
    }

    suspend fun quotes(symbols: List<String>): Flow<List<Quote>> {
        return flowOf(symbols.map {
            quoteRepository.quote(it)
        })
    }
}

data class FavedQuote(
    val symbol: String,
    val current: Double,
    val open: Double,
)