package com.rafag.stonks.data.favourites.internal

import com.stonks.db.StonksDatabase

internal class FavouritesPersistence(
    private val db: StonksDatabase,
) {

    fun save(symbol: String) = db.favouritesQuoteQueries.save(symbol)

    fun unsave(symbol: String) = db.favouritesQuoteQueries.unsave(symbol)
}