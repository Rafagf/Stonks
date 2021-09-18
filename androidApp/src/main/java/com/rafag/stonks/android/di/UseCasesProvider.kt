package com.rafag.stonks.android.di

import com.rafag.stonks.android.faved.domain.FetchFavedQuotesUseCase
import com.rafag.stonks.android.search.domain.SearchStonksUseCase
import com.rafag.stonks.android.search.domain.ToggleFavouriteUseCase

/**
 * Doing Manual DI for simplicity, migrate to Dagger Hilt
 */
class UseCasesProvider(repositoryProvider: RepositoryProvider) {

    val searchStonksUseCase = SearchStonksUseCase(
        searchRepository = repositoryProvider.searchRepository,
        favouritesRepository = repositoryProvider.favouritesRepository,
    )

    val toggleFavouriteUseCase = ToggleFavouriteUseCase(
        favouritesRepository = repositoryProvider.favouritesRepository,
    )

    val fetchSavedQuotesUseCase = FetchFavedQuotesUseCase(
        favouritesRepository = repositoryProvider.favouritesRepository,
        quoteRepository = repositoryProvider.quoteRepository,
    )
}
