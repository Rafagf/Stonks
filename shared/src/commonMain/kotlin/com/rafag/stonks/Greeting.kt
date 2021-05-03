package com.rafag.stonks

class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}