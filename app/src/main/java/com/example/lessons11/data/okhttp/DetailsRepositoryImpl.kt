package com.example.lessons11.data.okhttp

import okhttp3.Callback

class DetailsRepositoryImpl(private val remoteDataSource: RemoteDataSource):DetailsRepository {
    override fun GetWeatherDetailsFromServer(requestLink: String, callback: Callback) {
      remoteDataSource.getWeatherDetails(requestLink,callback)
    }
}