package com.example.lessons11.data.fake

import com.example.lessons11.data.fake.model.Weather

interface Repository {
    fun getWeatherFromServer(): Weather
    fun getWeatherFromLocalStorage():List<Weather>
}