package com.rafag.stonks

import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.client.request.request
import io.ktor.http.HttpMethod
import io.ktor.http.HttpMethod.*
import io.ktor.http.Url
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

private const val BASE_URL = "https://finnhub.io/api/v1/"
private const val TOKEN = "&token=c1o8a9237fkqrr9sc3a0"

class Api {

    private val client = HttpClient {
        install(JsonFeature) {
            serializer = KotlinxSerializer(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
        install(Logging) {
            level = LogLevel.HEADERS
            logger = object : Logger {
                override fun log(message: String) {
                    print("Api: message = $message")
                }
            }
        }
    }

    suspend fun search(input: String): String {
        val searchRequest = ApiRequests.search(input)
        return client.request(searchRequest.toUrl()) {
            method = searchRequest.method
        }
    }

    suspend fun quote(input: String): String {
        val quoteRequest = ApiRequests.quote(input)
        return client.request(quoteRequest.toUrl()) {
            method = quoteRequest.method
        }
    }
}

object ApiRequests {

    fun quote(symbol: String) = HttpRequest(
        method = HttpMethod.Get,
        path = "quote?symbol=${symbol}"
    )

    fun search(input: String) = HttpRequest(
        method = HttpMethod.Get,
        path = "search?q=${input}"
    )
}




fun HttpRequest.toUrl() = Url("${BASE_URL}${this.path}${TOKEN}")