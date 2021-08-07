package com.rafag.stonks.data.favourites.internal

import com.stonks.db.StonksDatabase

internal class FavouritesPersistence(
    private val db: StonksDatabase,
) {

    fun save(symbol: String) = db.favouritesQuoteQueries.save(symbol).executeAsOne()

    fun unsafe(symbol: String) = db.favouritesQuoteQueries.unsave(symbol)
}