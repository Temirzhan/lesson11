package com.example.lessons11.data.retrofit

import com.example.lessons11.data.fake.model.WeatherDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface WeatherApi{
    @GET("v2/forecast")
    fun getWether(
        @Header("X-Yandex-Weather-Key") token:String,
        @Query("lat") lat:Double,
        @Query("lon") lon:Double,
    ): Call<WeatherDTO>










}