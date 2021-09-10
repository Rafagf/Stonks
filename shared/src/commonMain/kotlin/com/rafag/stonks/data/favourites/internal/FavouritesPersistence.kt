package com.rafag.stonks.data.favourites.internal

import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import com.stonks.db.StonksDatabase

internal class FavouritesPersistence(
    private val db: StonksDatabase,
) {

    fun getAll() = db.dbFavouritesQueries.getAll().asFlow().mapToList()

    fun save(symbol: String) = db.dbFavouritesQueries.save(symbol)

    fun unsave(symbol: String) = db.dbFavouritesQueries.unsave(symbol)
}