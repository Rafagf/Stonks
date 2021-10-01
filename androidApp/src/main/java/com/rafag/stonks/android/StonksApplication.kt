package com.rafag.stonks.android

import android.app.Application
import com.rafag.stonks.android.di.RepositoryProvider
import com.rafag.stonks.android.di.UseCasesProvider
import com.rafag.stonks.android.di.ViewModelsProvider

/**
 * Doing Manual DI for simplicity, migrate to Dagger Hilt
 */
class StonksApplication : Application() {

    val repositoryProvider = RepositoryProvider(this)
    val useCaseProvider = UseCasesProvider(repositoryProvider)
    val viewModelsProvider = ViewModelsProvider(useCaseProvider)
}