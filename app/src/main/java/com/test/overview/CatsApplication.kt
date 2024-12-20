package com.test.overview

import android.app.Application
import com.test.overview.data.AppContainer
import com.test.overview.data.DefaultAppContainer

class CatsApplication : Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}