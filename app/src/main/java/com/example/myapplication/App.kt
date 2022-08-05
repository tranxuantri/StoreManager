package com.example.myapplication

import android.app.Application
import timber.log.Timber

//import android.app.Application
//import dagger.hilt.android.HiltAndroidApp
//import timber.log.Timber
//
//@HiltAndroidApp
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}