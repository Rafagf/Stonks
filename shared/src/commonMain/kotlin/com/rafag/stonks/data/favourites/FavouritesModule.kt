package com.rafag.stonks.data.favourites

import com.rafag.stonks.data.favourites.internal.FavouritesPersistence
import com.rafag.stonks.data.favourites.internal.FavouritesRepositoryImpl
import com.stonks.db.StonksDatabase

class FavouritesModule(private val db: StonksDatabase) {

    fun repository(): FavouritesRepository {
        return FavouritesRepositoryImpl(
            persistence = FavouritesPersistence(db)
        )
    }
}