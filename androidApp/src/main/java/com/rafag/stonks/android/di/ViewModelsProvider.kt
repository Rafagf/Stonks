package com.rafag.stonks.android.di

import android.app.Activity
import com.rafag.stonks.android.StonksApplication
import com.rafag.stonks.android.search.presentation.SearchViewModel

class ViewModelsProvider(useCasesProvider: UseCasesProvider) {

    val searchViewModel = SearchViewModel(useCasesProvider.searchStonksUseCase)
}

fun Activity.searchViewModel() = (application as StonksApplication).viewModelsProvider.searchViewModel