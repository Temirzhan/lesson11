package com.example.lessons11.data.okhttp

import okhttp3.Callback

interface DetailsRepository {
    fun GetWeatherDetailsFromServer( requestLink:String, callback: Callback)
}

