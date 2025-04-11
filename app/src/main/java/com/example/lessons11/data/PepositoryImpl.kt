package com.example.lessons11.data

import com.example.lessons11.data.model.Weather

class PepositoryImpl:Repository {
    override fun getWeatherFromServer(): Weather {
       return Weather()
    }

    override fun getWeatherFromLocalStorage(): Weather {
       return Weather()
    }
}