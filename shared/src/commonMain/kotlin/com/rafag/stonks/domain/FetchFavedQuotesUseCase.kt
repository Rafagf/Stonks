package com.rafag.stonks.domain

import com.rafag.stonks.data.favourites.FavouritesRepository
import com.rafag.stonks.data.quote.Quote
import com.rafag.stonks.data.quote.QuoteRepository
import com.rafag.stonks.data.quote.internal.ErrorFetchingQuote
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class FetchFavedQuotesUseCase(
    private val quoteRepository: QuoteRepository,
    private val favouritesRepository: FavouritesRepository
) {

    @OptIn(FlowPreview::class)
    suspend fun invoke(): Flow<List<FavedQuote>> {
        return favouritesRepository.getAll().flatMapConcat {
            quotes(it).map {
                it.map {
                    FavedQuote(it.symbol, it.current, it.open)
                }
            }
        }
    }

    private suspend fun quotes(symbols: List<String>): Flow<List<Quote>> {
        return flowOf(symbols.mapNotNull {
            /**
             * The stock provider (FinnHub) returns randoms 403 for some foreign stocks in their free tier.
             * We ignore the stock because this is a pet project so we look simplicity and we don't want
             * to fail the whole page because of it - https://github.com/finnhubio/Finnhub-API/issues/372
             */
            try {
                quoteRepository.quote(it)
            } catch (exception: ErrorFetchingQuote) {
                null
            }
        })
    }
}

data class FavedQuote(
    val symbol: String,
    val current: Double,
    val open: Double,
)