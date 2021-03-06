package com.rafag.stonks.internal.fixtures

import com.rafag.stonks.internal.domain.repositories.Search
import com.rafag.stonks.internal.domain.repositories.SearchItem
import com.rafag.stonks.internal.data.repositories.search.ApiSearchItemResponse
import com.rafag.stonks.internal.data.repositories.search.ApiSearchResponse

internal object SearchFixture {

    fun anApiSearch() = ApiSearchResponse(
        listOf(anApiSearchItem())
    )

    private fun anApiSearchItem() = ApiSearchItemResponse(
        description = "a-description",
        displaySymbol = "a-display-symbol",
        symbol = "symbol",
        type = "a-type"
    )

    fun aSearch(list: List<SearchItem> = emptyList()) = Search(
        list = list
    )

    fun aSearchItem(symbol: String, name: String) = SearchItem(
        symbol = symbol,
        name = name,
        displaySymbol = symbol,
        type = "type"
    )
}
