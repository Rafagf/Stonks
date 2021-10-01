package com.rafag.stonks.android

import android.app.Application
import com.rafag.stonks.android.di.ViewModelsProvider
import com.rafag.stonks.api.StonksHttpClient
import com.rafag.stonks.db.DatabaseDriverFactory
import com.rafag.stonks.domain.UseCasesProvider
import com.stonks.db.StonksDatabase

/**
 * Doing Manual DI for simplicity, migrate to Dagger Hilt
 */
class StonksApplication : Application() {


    lateinit var viewModelsProvider: ViewModelsProvider

    override fun onCreate() {
        super.onCreate()
        setupDI()
    }

    private fun setupDI() {
        val driver = DatabaseDriverFactory(applicationContext).createDriver()
        val db = StonksDatabase(driver)
        val useCaseProvider = UseCasesProvider(db = db)
        viewModelsProvider = ViewModelsProvider(useCaseProvider)
    }
}