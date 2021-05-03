package com.rafag.stonks

import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class ApiWrapper {
    fun testRequest(completion: (String) -> Unit) {
        MainScope().launch {
            val result = Api().testQuery()
            completion(result)
        }
    }
}