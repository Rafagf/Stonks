package com.rafag.stonks.android.di

import com.rafag.stonks.android.search.domain.SearchStonksUseCase

class UseCasesProvider(repositoryProvider: RepositoryProvider) {

    val searchStonksUseCase = SearchStonksUseCase(
        searchRepository = repositoryProvider.searchRepository,
        favouritesRepository = repositoryProvider.favouritesRepository
    )
}
