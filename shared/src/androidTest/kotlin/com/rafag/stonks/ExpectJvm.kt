package com.rafag.stonks

actual fun <T> runBlocking(block: suspend () -> T): T = kotlinx.coroutines.runBlocking { block() }

actual inline fun <reified T : Any> mock(): T {
    return com.nhaarman.mockitokotlin2.mock()
}

actual fun <T> whenever(methodCall: T): OngoingStubbing<T> {
    val mock = com.nhaarman.mockitokotlin2.whenever(methodCall)
    return wrapMock(mock)
}

fun <T> wrapMock(mock: org.mockito.stubbing.OngoingStubbing<T>): OngoingStubbing<T> {
    return object : OngoingStubbing<T> {
        override fun thenReturn(value: T) = wrapMock(mock.thenReturn(value))
        override fun thenReturn(value: T, vararg values: T) = wrapMock(mock.thenReturn(value, *values))
        override fun thenThrow(vararg throwables: Throwable) = wrapMock(mock.thenThrow(*throwables))
    }
}

actual fun <T> verify(methodCall: T): T {
    return com.nhaarman.mockitokotlin2.verify(methodCall)
}

actual fun <T> verifyNever(methodCall: T): T {
    return com.nhaarman.mockitokotlin2.verify(methodCall, com.nhaarman.mockitokotlin2.never())
}

actual fun <T : Any> verifyZeroInteractions(mock: T) {
    return com.nhaarman.mockitokotlin2.verifyZeroInteractions(mock)
}
