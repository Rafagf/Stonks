package com.rafag.stonks.android

import android.app.Application

class StonksApplication : Application() {

    val repositoryProvider = RepositoryProvider(this)
}