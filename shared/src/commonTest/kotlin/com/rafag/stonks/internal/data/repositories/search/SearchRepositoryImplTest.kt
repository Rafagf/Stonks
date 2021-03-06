package com.rafag.stonks.internal.data.repositories.search

import com.rafag.stonks.internal.fixtures.SearchFixture
import com.rafag.stonks.mockHttp
import com.rafag.stonks.runBlocking
import kotlin.test.Test
import kotlin.test.assertEquals

private const val A_SEARCH = "GOO"
private val AN_API_SEARCH = SearchFixture.anApiSearch()

class SearchRepositoryImplTest {

    private val httpClient = mockHttp()
    private val repository = SearchRepositoryImpl(httpClient.instance)

    @Test
    fun `given api success when searching then return data`() {
        val request = SearchApi.searchRequest(A_SEARCH)
        httpClient.addHandler(request, AN_API_SEARCH)

        runBlocking {
            val result = repository.search(A_SEARCH)
            assertEquals(result, AN_API_SEARCH.toModel())
        }
    }
}