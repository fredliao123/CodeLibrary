package com.example.fredliao.codelibrary

import android.app.Application
import timber.log.Timber

class BaseApplication : Application() {

    lateinit var serviceContainer: ServiceContainer

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        } else {

        }
        serviceContainer = ServiceContainer(this)
    }
}