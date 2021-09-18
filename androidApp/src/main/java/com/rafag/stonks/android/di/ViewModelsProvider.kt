package com.rafag.stonks.android.di

import android.app.Activity
import com.rafag.stonks.android.StonksApplication
import com.rafag.stonks.android.faved.presentation.FavedViewModel
import com.rafag.stonks.android.search.presentation.SearchViewModel

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

fun Activity.searchViewModel() = (application as StonksApplication).viewModelsProvider.searchViewModel
fun Activity.favedViewModel() = (application as StonksApplication).viewModelsProvider.favedViewModel