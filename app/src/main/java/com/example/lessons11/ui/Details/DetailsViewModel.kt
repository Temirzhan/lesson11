package com.example.lessons11.ui.Details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lessons11.data.fake.model.FactDTO
import com.example.lessons11.data.fake.model.Weather
import com.example.lessons11.data.fake.model.WeatherDTO
import com.example.lessons11.data.fake.model.getDefault
import com.example.lessons11.data.okhttp.DetailsRepositoryImpl
import com.example.lessons11.data.okhttp.RemoteDataSource
import com.example.lessons11.ui.home.AppState
import com.google.gson.Gson
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

private const val SERVER_ERROR = "Ошибка сервера"
private const val REQUEST_ERROR = "Ошибка запроса на сервер"
private const val CORRUPTED_DATA = "Неполные данные"

class DetailsViewModel(
    private val detailsLiveData: MutableLiveData<AppState> = MutableLiveData(),
    private val detailsRepositoryImpl: DetailsRepositoryImpl = DetailsRepositoryImpl(
        RemoteDataSource()
    )
): ViewModel() {

    fun getLiveData () = detailsLiveData

    fun getWeatherDetailsFromServer(requestLink:String){
        detailsLiveData.value = AppState.Loading
        detailsRepositoryImpl.GetWeatherDetailsFromServer(requestLink, callback)
    }

    private val callback = object :Callback {
        override fun onFailure(call: Call, e: IOException) {
            detailsLiveData.postValue(AppState.Error(Throwable(REQUEST_ERROR)))
        }

        override fun onResponse(call: Call, response: Response) {
           val serverResponse:String? = response.body()?.string()
            detailsLiveData.postValue(
                if (response.isSuccessful&&serverResponse!= null){
                    checkResponse(serverResponse)
                }else{
                    AppState.Error(Throwable(SERVER_ERROR))
                }
            )
        }

        private fun checkResponse(serverResponse:String):AppState{
            val weatherDTO: WeatherDTO =
                Gson().fromJson(serverResponse, WeatherDTO::class.java)
            val fact = weatherDTO.fact
            return if (fact == null || fact.temp == null ) {
                AppState.Error(Throwable(CORRUPTED_DATA))
            } else {
                AppState.Success(convertDtoToModel(weatherDTO))
            }
        }
    }

    fun convertDtoToModel(weatherDTO: WeatherDTO):List<Weather>{
        val fact:FactDTO = weatherDTO.fact!!
        return listOf(Weather(getDefault(), fact.temp!!))
    }
}