package com.example.lessons11.data.okhttp

import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request

private const val REQUEST_API_KEY = "X-Yandex-Weather-Key"

class RemoteDataSource {
    fun getWeatherDetails(requestLink:String,callback: Callback){
        val builder: Request.Builder = Request.Builder().apply {
            header(REQUEST_API_KEY,"af69b1d4-8888-4ba7-91ef-50ea5627fd65" )
            url(requestLink)
        }
        OkHttpClient().newCall(builder.build()).enqueue(callback)
    }
}