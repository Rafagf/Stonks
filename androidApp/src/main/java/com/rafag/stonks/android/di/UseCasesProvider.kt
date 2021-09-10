package com.rafag.stonks.android.di

import com.rafag.stonks.android.search.domain.SearchStonksUseCase
import com.rafag.stonks.android.search.domain.ToggleFavouriteUseCase

class UseCasesProvider(repositoryProvider: RepositoryProvider) {

    val searchStonksUseCase = SearchStonksUseCase(
        searchRepository = repositoryProvider.searchRepository,
        favouritesRepository = repositoryProvider.favouritesRepository
    )

    val toggleFavouriteUseCase = ToggleFavouriteUseCase(
        favouritesRepository = repositoryProvider.favouritesRepository
    )
}
