package com.rafag.stonks.internal.domain.usecases

import com.rafag.stonks.domain.usecases.FavedQuote
import com.rafag.stonks.domain.usecases.FetchFavedQuotesUseCase
import com.rafag.stonks.internal.domain.repositories.FavouritesRepository
import com.rafag.stonks.internal.domain.repositories.Quote
import com.rafag.stonks.internal.domain.repositories.QuoteRepository
import com.rafag.stonks.internal.data.repositories.quote.ErrorFetchingQuote
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

internal class FetchFavedQuotesUseCaseImpl(
    private val quoteRepository: QuoteRepository,
    private val favouritesRepository: FavouritesRepository
): FetchFavedQuotesUseCase {

    @OptIn(FlowPreview::class)
    override suspend fun invoke(): Flow<List<FavedQuote>> {
        return favouritesRepository.getAll().flatMapConcat { favedSymbols ->
            quotes(favedSymbols).map { quotes ->
                quotes.toFavedQuote()
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

fun List<Quote>.toFavedQuote() = this.map { it.toFavedQuote() }

fun Quote.toFavedQuote() = FavedQuote(
    symbol = this.symbol,
    current = this.current,
    open = this.open,
)
