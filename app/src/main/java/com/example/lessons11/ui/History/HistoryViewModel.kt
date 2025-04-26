package com.example.lessons11.ui.History

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lessons11.App.Companion.getHistoryDao
import com.example.lessons11.data.room.LocalRepository
import com.example.lessons11.data.room.LocalRepositoryImpl
import com.example.lessons11.ui.home.AppState

class HistoryViewModel(
    val historyLiveData: MutableLiveData<AppState> = MutableLiveData(),
    private val historyRepository: LocalRepository =
        LocalRepositoryImpl(getHistoryDao())
) : ViewModel() {
// Сверстать экран добавить туда Recycle View, Loading

    fun getAllHitory(){
        historyLiveData.value = AppState.Loading
        // Appstate Succes  и предать туда getAlllhistoty
    }

}