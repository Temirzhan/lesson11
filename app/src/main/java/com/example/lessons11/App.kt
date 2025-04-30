package com.example.lessons11

import android.app.Application


class App:Application() {

    override fun onCreate() {
        super.onCreate()
    }

    companion object {
        private var appInstance:App? = null
        private const val DB_NAME = "History.db"


    }
}