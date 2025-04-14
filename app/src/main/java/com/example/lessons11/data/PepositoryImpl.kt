package com.example.lessons11.data

import com.example.lessons11.data.model.Weather
import com.example.lessons11.data.model.getCities

class PepositoryImpl:Repository {
    override fun getWeatherFromServer(): Weather {
       return Weather()
    }

    override fun getWeatherFromLocalStorage(): List<Weather> {
       return getCities()
    }
}