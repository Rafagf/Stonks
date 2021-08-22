package com.rafag.stonks.android.di

import com.rafag.stonks.android.search.SearchStonksUseCase

class UseCasesProvider(repositoryProvider: RepositoryProvider) {

    val searchStonksUseCase = SearchStonksUseCase(repositoryProvider.searchRepository)
}
