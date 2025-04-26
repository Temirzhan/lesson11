package com.example.lessons11.data.okhttp

import com.example.lessons11.data.fake.model.WeatherDTO
import retrofit2.Callback


interface DetailsRepository {
    fun GetWeatherDetailsFromServer( lat:Double,lon:Double,callback: Callback<WeatherDTO>)
}

