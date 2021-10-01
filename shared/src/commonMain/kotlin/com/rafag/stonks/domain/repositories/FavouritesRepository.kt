package com.rafag.stonks.domain.repositories

import kotlinx.coroutines.flow.Flow

interface FavouritesRepository {

    suspend fun getAll(): Flow<List<String>>
    suspend fun save(symbol: String)
    suspend fun unsave(symbol: String)
}