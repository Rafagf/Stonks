package com.rafag.stonks.data.favourites

import com.rafag.stonks.api.StonksHttpClient
import com.rafag.stonks.data.favourites.internal.FavouritesPersistence
import com.rafag.stonks.data.favourites.internal.FavouritesRepositoryImpl
import com.rafag.stonks.data.quote.internal.QuotePersistence
import com.stonks.db.StonksDatabase

class FavouritesModule(
    private val httpClient: StonksHttpClient = StonksHttpClient(),
    private val db: StonksDatabase,
) {

    fun repository(): FavouritesRepository {
        return FavouritesRepositoryImpl(
            persistence = FavouritesPersistence(db)
        )
    }
}