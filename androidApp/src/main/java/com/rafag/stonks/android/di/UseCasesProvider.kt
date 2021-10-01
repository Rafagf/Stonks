package com.rafag.stonks.android.di

import android.content.Context
import com.rafag.stonks.data.favourites.FavouritesModule
import com.rafag.stonks.data.quote.QuoteModule
import com.rafag.stonks.data.search.SearchModule
import com.rafag.stonks.db.DatabaseDriverFactory
import com.rafag.stonks.domain.FetchFavedQuotesUseCase
import com.rafag.stonks.domain.SearchStonksUseCase
import com.rafag.stonks.domain.ToggleFavouriteUseCase
import com.stonks.db.StonksDatabase

/**
 * Doing Manual DI for simplicity, migrate to Dagger Hilt
 */
class UseCasesProvider(applicationContext: Context) {

    private val driver = DatabaseDriverFactory(applicationContext).createDriver()
    private val db = StonksDatabase(driver)

    private val quoteRepository = QuoteModule(db = db).repository()
    private val favouritesRepository = FavouritesModule(db = db).repository()
    private val searchRepository = SearchModule().repository()

    val searchStonksUseCase = SearchStonksUseCase(
        searchRepository = searchRepository,
        favouritesRepository = favouritesRepository,
    )

    val toggleFavouriteUseCase = ToggleFavouriteUseCase(
        favouritesRepository =favouritesRepository,
    )

    val fetchSavedQuotesUseCase = FetchFavedQuotesUseCase(
        favouritesRepository = favouritesRepository,
        quoteRepository = quoteRepository,
    )
}
