package com.rafag.stonks.android.di

import android.content.Context
import com.rafag.stonks.data.favourites.FavouritesModule
import com.rafag.stonks.data.quote.QuoteModule
import com.rafag.stonks.data.search.SearchModule
import com.rafag.stonks.db.DatabaseDriverFactory
import com.stonks.db.StonksDatabase

/**
 * Doing Manual DI for simplicity, migrate to Dagger Hilt
 */
class RepositoryProvider(applicationContext: Context) {

    private val driver = DatabaseDriverFactory(applicationContext).createDriver()
    private val db = StonksDatabase(driver)

    val quoteRepository = QuoteModule(db = db).repository()
    val favouritesRepository = FavouritesModule(db = db).repository()
    val searchRepository = SearchModule().repository()
}
