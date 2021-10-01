package com.rafag.stonks.android.di

import android.app.Application
import com.rafag.stonks.android.StonksApplication
import com.rafag.stonks.android.faved.presentation.FavedViewModel
import com.rafag.stonks.android.search.presentation.SearchViewModel
import com.rafag.stonks.domain.UseCasesProvider

/**
 * Doing Manual DI for simplicity, migrate to Dagger Hilt
 */
class ViewModelsProvider(useCasesProvider: UseCasesProvider) {

    val searchViewModel = SearchViewModel(
        searchUseCase = useCasesProvider.searchStonksUseCase(),
        toggleFavouriteUseCase = useCasesProvider.toggleFavouritesUseCase(),
    )

    val favedViewModel = FavedViewModel(
        fetchFavedQuotesUseCase = useCasesProvider.fetchFavedQuotesUseCase(),
        toggleFavouriteUseCase = useCasesProvider.toggleFavouritesUseCase(),
    )
}

fun Application.searchViewModel() = (this as StonksApplication).viewModelsProvider.searchViewModel
fun Application.favedViewModel() = (this as StonksApplication).viewModelsProvider.favedViewModel