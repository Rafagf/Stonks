package com.rafag.stonks.android.di

import com.rafag.stonks.android.faved.domain.FetchFavedQuotesUseCase
import com.rafag.stonks.android.faved.domain.ToggleFavouriteUseCase
import com.rafag.stonks.android.search.domain.SearchStonksUseCase
import com.rafag.stonks.android.search.domain.ToggleFavouriteUseCaseSearch

/**
 * Doing Manual DI for simplicity, migrate to Dagger Hilt
 */
class UseCasesProvider(repositoryProvider: RepositoryProvider) {

    val searchStonksUseCase = SearchStonksUseCase(
        searchRepository = repositoryProvider.searchRepository,
        favouritesRepository = repositoryProvider.favouritesRepository,
    )

    val toggleFavouriteUseCaseSearch = ToggleFavouriteUseCaseSearch(
        favouritesRepository = repositoryProvider.favouritesRepository,
    )

    val toggleFavouriteUseCaseFaved = ToggleFavouriteUseCase(
        favouritesRepository = repositoryProvider.favouritesRepository,
    )

    val fetchSavedQuotesUseCase = FetchFavedQuotesUseCase(
        favouritesRepository = repositoryProvider.favouritesRepository,
        quoteRepository = repositoryProvider.quoteRepository,
    )
}
