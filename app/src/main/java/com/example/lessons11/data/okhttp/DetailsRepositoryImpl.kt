package com.example.lessons11.data.okhttp

import com.example.lessons11.data.fake.model.WeatherDTO
import com.example.lessons11.data.retrofit.RemoteDataSource
import retrofit2.Callback


class DetailsRepositoryImpl(private val remoteDataSource: RemoteDataSource):DetailsRepository {
    override fun GetWeatherDetailsFromServer(lat:Double,lon:Double, callback: Callback<WeatherDTO>) {
      remoteDataSource.getWetaherDetails(lat = lat, lon = lon, callback = callback)
    }
}