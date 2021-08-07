package com.rafag.stonks.data.favourites.internal

import com.stonks.db.StonksDatabase

internal class FavouritesPersistence(
    private val db: StonksDatabase,
) {

    fun getAll() = db.dbFavouritesQueries.getAll().executeAsList()

    fun save(symbol: String) = db.dbFavouritesQueries.save(symbol)

    fun unsave(symbol: String) = db.dbFavouritesQueries.unsave(symbol)
}