package com.rafag.stonks

import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging
import io.ktor.client.request.get
import io.ktor.client.request.request
import io.ktor.http.HttpMethod
import io.ktor.http.Url
import kotlinx.serialization.json.Json

class Ktor {

    private val client = HttpClient {
        install(JsonFeature) {
            serializer = KotlinxSerializer(Json {
                prettyPrint = true
                isLenient = true
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

    private val testQuery = Url("https://finnhub.io/api/v1/search?q=apple&token=c1o8a9237fkqrr9sc3a0")

    suspend fun testQuery(): String {
        val result: String = client.request(testQuery) {
            method = HttpMethod.Get
        }

        return result
    }
}