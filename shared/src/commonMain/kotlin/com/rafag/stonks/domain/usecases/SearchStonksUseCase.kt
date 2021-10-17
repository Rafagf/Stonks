package com.rafag.stonks.domain.usecases

import kotlinx.coroutines.flow.Flow

interface SearchStonksUseCase {

    suspend fun invoke(query: String): Flow<List<StonkSearch>>
}

data class StonkSearch(
    val name: String,
    val symbol: String,
    val faved: Boolean
)