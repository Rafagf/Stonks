package com.rafag.stonks.domain

import com.rafag.stonks.api.StonksHttpClient
import com.rafag.stonks.data.favourites.FavouritesRepository
import com.rafag.stonks.data.favourites.internal.FavouritesPersistence
import com.rafag.stonks.data.favourites.internal.FavouritesRepositoryImpl
import com.rafag.stonks.data.quote.QuoteRepository
import com.rafag.stonks.data.quote.internal.QuotePersistence
import com.rafag.stonks.data.quote.internal.QuoteRepositoryImpl
import com.rafag.stonks.data.search.SearchRepository
import com.rafag.stonks.data.search.internal.SearchRepositoryImpl
import com.stonks.db.StonksDatabase

class UseCasesProvider(
    private val httpClient: StonksHttpClient = StonksHttpClient(),
    val db: StonksDatabase,
) {

    fun fetchFavedQuotesUseCase() = FetchFavedQuotesUseCase(
        quoteRepository = quoteRepository(),
        favouritesRepository = favouritesRepository(),
    )

    fun searchStonksUseCase() = SearchStonksUseCase(
        searchRepository = searchRepository(),
        favouritesRepository = favouritesRepository(),
    )

    fun toggleFavouritesUseCase() = ToggleFavouriteUseCase(
        favouritesRepository = favouritesRepository()
    )

    private fun quoteRepository(): QuoteRepository {
        return QuoteRepositoryImpl(
            httpClient = httpClient,
            persistence = QuotePersistence(db)
        )
    }

    private fun favouritesRepository(): FavouritesRepository {
        return FavouritesRepositoryImpl(
            persistence = FavouritesPersistence(db)
        )
    }

    private fun searchRepository(): SearchRepository {
        return SearchRepositoryImpl(httpClient)
    }
}