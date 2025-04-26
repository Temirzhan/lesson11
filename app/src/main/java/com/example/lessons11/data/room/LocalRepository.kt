package com.example.lessons11.data.room

import com.example.lessons11.data.fake.model.Weather

interface LocalRepository {
    fun getAllHistory():List<Weather>
    fun saveEntity(weather: Weather)
}