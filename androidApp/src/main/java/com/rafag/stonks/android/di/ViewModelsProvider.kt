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
        toggleFavouriteUseCase = useCasesProvider.toggleFavouriteUseCase,
    )

    val favedViewModel = FavedViewModel(
        fetchFavedQuotesUseCase = useCasesProvider.fetchSavedQuotesUseCase,
        toggleFavouriteUseCase = useCasesProvider.toggleFavouriteUseCase,
    )
}

fun Application.searchStonksViewModel() = (this as StonksApplication).viewModelsProvider.searchViewModel
fun Application.favedQuotesViewModel() = (this as StonksApplication).viewModelsProvider.favedViewModel