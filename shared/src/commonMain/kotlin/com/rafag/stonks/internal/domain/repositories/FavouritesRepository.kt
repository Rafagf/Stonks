package com.rafag.stonks.internal.domain.repositories

import kotlinx.coroutines.flow.Flow

internal interface FavouritesRepository {

    suspend fun getAll(): Flow<List<String>>
    suspend fun save(symbol: String)
    suspend fun unsave(symbol: String)
}