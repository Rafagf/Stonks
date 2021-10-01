package com.rafag.stonks.android.di

import android.app.Application
import com.rafag.stonks.android.StonksApplication
import com.rafag.stonks.android.faved.presentation.FavedViewModel
import com.rafag.stonks.android.search.presentation.SearchViewModel

/**
 * Doing Manual DI for simplicity, migrate to Dagger Hilt
 */
class ViewModelsProvider(useCasesProvider: UseCasesProvider) {

    val searchViewModel = SearchViewModel(
        searchUseCase = useCasesProvider.searchStonksUseCase,
        toggleFavouriteUseCase = useCasesProvider.toggleFavouriteUseCaseSearch,
    )

    val favedViewModel = FavedViewModel(
        fetchFavedQuotesUseCase = useCasesProvider.fetchSavedQuotesUseCase,
        toggleFavouriteUseCase = useCasesProvider.toggleFavouriteUseCaseFaved,
    )
}

fun Application.searchViewModel() = (this as StonksApplication).viewModelsProvider.searchViewModel
fun Application.favedViewModel() = (this as StonksApplication).viewModelsProvider.favedViewModel