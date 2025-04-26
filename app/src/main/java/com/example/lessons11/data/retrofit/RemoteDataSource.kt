package com.example.lessons11.data.retrofit

import com.example.lessons11.data.fake.model.WeatherDTO
import com.google.gson.GsonBuilder
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RemoteDataSource {

    private val weatherApi = Retrofit.Builder()
        .baseUrl("https://api.weather.yandex.ru/")
        .addConverterFactory(
            GsonConverterFactory.create(
                GsonBuilder().setLenient().create()
            )
        ).build().create(WeatherApi::class.java)

    fun getWetaherDetails(lat:Double,lon:Double, callback: Callback<WeatherDTO>){
        weatherApi.getWether(token = "af69b1d4-8888-4ba7-91ef-50ea5627fd65", lat = lat, lon = lon).enqueue(callback)
    }
}