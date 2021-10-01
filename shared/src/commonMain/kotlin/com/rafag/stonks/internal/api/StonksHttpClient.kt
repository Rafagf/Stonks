package com.rafag.stonks.internal.api

import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging
import io.ktor.client.request.request
import io.ktor.http.HttpMethod
import kotlinx.serialization.json.Json

private const val BASE_URL = "https://finnhub.io/api/v1/"

internal class StonksHttpClient(
    private val client: HttpClient = defaultHttpClient(),
    private val baseUrl: String = BASE_URL,
) {

    internal suspend inline fun <reified T> execute(request: HttpRequest<T>): T {
        return client.request("$baseUrl${request.url}$API_TOKEN") {
            this.method = HttpMethod.Get
            this.body = request.body
        }
    }
}

private fun defaultHttpClient() = HttpClient {
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
