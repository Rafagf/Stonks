package com.rafag.stonks

actual fun <T> runBlocking(block: suspend () -> T): T {
    throw Throwable("To be implemented")
}

actual inline fun <reified T : Any> mock(): T {
    throw Throwable("To be implemented")
}

actual fun <T> whenever(methodCall: T): OngoingStubbing<T> {
    throw Throwable("To be implemented")
}

actual fun <T> verify(methodCall: T): T {
    throw Throwable("To be implemented")
}

actual fun <T> verifyNever(methodCall: T): T {
    throw Throwable("To be implemented")
}

actual fun <T : Any> verifyZeroInteractions(mock: T) {
    throw Throwable("To be implemented")
}
