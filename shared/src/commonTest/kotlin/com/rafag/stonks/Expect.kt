package com.rafag.stonks

import com.rafag.stonks.internal.api.MockHttpClient

expect fun <T> runBlocking(block: suspend () -> T): T

expect inline fun <reified T : Any> mock(): T

expect fun <T> whenever(methodCall: T): OngoingStubbing<T>

expect fun <T> verify(methodCall: T): T

expect fun <T> verifyNever(methodCall: T): T

expect fun <T : Any> verifyZeroInteractions(mock: T)

interface OngoingStubbing<T> {

    fun thenReturn(value: T): OngoingStubbing<T>
    fun thenReturn(value: T, vararg values: T): OngoingStubbing<T>
    fun thenThrow(vararg throwables: Throwable): OngoingStubbing<T>
}

fun mockHttp(): MockHttpClient {
    return MockHttpClient()
}
