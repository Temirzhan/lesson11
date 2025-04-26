package com.example.lessons11.data.room

import com.example.lessons11.Utils
import com.example.lessons11.data.fake.model.Weather


class LocalRepositoryImpl(private val localDataSource: HistoryDao):LocalRepository {
    override fun getAllHistory(): List<Weather> {
        return  Utils.convertHistoryEntityToWeather(localDataSource.all())
    }

    override fun saveEntity(weather: Weather) {
        localDataSource.insert(Utils.convertWeatherToEntity(weather))
    }
}