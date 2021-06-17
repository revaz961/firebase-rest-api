package com.example.apiauthentication

import android.app.Application

class App:Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    external fun APIKey(): String

    companion object {
        lateinit var instance: App
            private set

        // Used to load the 'native-lib' library on application startup.
        init {
            System.loadLibrary("native-lib")
        }
    }
}