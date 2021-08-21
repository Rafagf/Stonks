package com.rafag.stonks.android

import android.app.Activity
import android.content.Context
import com.rafag.stonks.data.favourites.FavouritesModule
import com.rafag.stonks.data.quote.QuoteModule
import com.rafag.stonks.data.search.SearchModule
import com.rafag.stonks.db.DatabaseDriverFactory
import com.stonks.db.StonksDatabase

class RepositoryProvider(applicationContext: Context) {

    private val driver = DatabaseDriverFactory(applicationContext).createDriver()
    private val db = StonksDatabase(driver)

    val quoteRepository = QuoteModule(db = db).repository()
    val favouritesRepository = FavouritesModule(db = db).repository()
    val searchRepository = SearchModule().repository()
}

fun Activity.quoteRepository() = (application as StonksApplication).repositoryProvider.searchRepository
fun Activity.favouritesRepository() = (application as StonksApplication).repositoryProvider.searchRepository
fun Activity.searchRepository() = (application as StonksApplication).repositoryProvider.searchRepository