package com.rafag.stonks.domain.usecases

import com.rafag.stonks.internal.domain.repositories.FavouritesRepository
import com.rafag.stonks.internal.domain.repositories.QuoteRepository
import com.rafag.stonks.internal.domain.repositories.SearchRepository
import com.rafag.stonks.internal.data.httpclient.StonksHttpClient
import com.rafag.stonks.internal.data.repositories.favourites.FavouritesPersistence
import com.rafag.stonks.internal.data.repositories.favourites.FavouritesRepositoryImpl
import com.rafag.stonks.internal.data.repositories.quote.QuotePersistence
import com.rafag.stonks.internal.data.repositories.quote.QuoteRepositoryImpl
import com.rafag.stonks.internal.data.repositories.search.SearchRepositoryImpl
import com.rafag.stonks.internal.domain.usecases.FetchFavedQuotesUseCaseImpl
import com.rafag.stonks.internal.domain.usecases.SearchStonksUseCaseImpl
import com.rafag.stonks.internal.domain.usecases.ToggleFavouriteUseCaseImpl
import com.stonks.db.StonksDatabase

class UseCasesProvider(
    val db: StonksDatabase,
) {

    private val httpClient: StonksHttpClient = StonksHttpClient()

    fun fetchFavedQuotesUseCase(): FetchFavedQuotesUseCase = FetchFavedQuotesUseCaseImpl(
        quoteRepository = quoteRepository(),
        favouritesRepository = favouritesRepository(),
    )

    fun searchStonksUseCase(): SearchStonksUseCase = SearchStonksUseCaseImpl(
        searchRepository = searchRepository(),
        favouritesRepository = favouritesRepository(),
    )

    fun toggleFavouritesUseCase(): ToggleFavouriteUseCase = ToggleFavouriteUseCaseImpl(
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