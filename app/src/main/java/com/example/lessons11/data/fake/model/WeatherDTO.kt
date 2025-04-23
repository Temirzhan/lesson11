package com.example.lessons11.data.fake.model

data class WeatherDTO(
    val fact: FactDTO?
)


data class FactDTO(
    val temp: Int?,
    val feels_like: Int?,
    val condition: String?
)


