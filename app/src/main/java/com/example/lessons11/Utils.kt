package com.example.lessons11

import com.example.lessons11.data.fake.model.City
import com.example.lessons11.data.fake.model.Weather
import com.example.lessons11.data.room.HistoryEntity

class Utils {

    companion object{
        fun convertHistoryEntityToWeather(enityList: List<HistoryEntity>):List<Weather>{
            return enityList.map {
                Weather(City(it.city,"0.0","0.0"), it.temperature)
            }
        }

        fun convertWeatherToEntity(weather: Weather):HistoryEntity{
            return HistoryEntity(0,weather.city.city,weather.temperature)
        }
    }
}