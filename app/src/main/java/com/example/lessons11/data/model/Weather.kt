package com.example.lessons11.data.model

data class Weather(
    val city: City = getDefault(),
    val temperature:Int = 0
)

fun getDefault() = City("Астана" , "2312123" , "123123123")
