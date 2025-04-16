package com.example.lessons11.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Weather(
    val city: City = getDefault(),
    val temperature:Int = 0
) : Parcelable

fun getDefault() = City("Астана" , "2312123" , "123123123")

fun getCities()=  listOf(
        Weather(City("Астана", "2312123" , "123123123") , 1),
        Weather(City("Павлодар", "2341124" , "31423123") , 2),
        Weather(City("Караганда", "123123" , "4232412") , 3),
        Weather(City("Атырау", "1235124" , "13124125") , 4),
        Weather(City("Костанай", "1241512" , "435234") , 5),
        Weather(City("Алматы", "142232" , "235234234") , 6)
)
