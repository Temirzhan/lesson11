package com.example.lessons11

import android.app.Application
import androidx.room.Room
import com.example.lessons11.data.room.HistoryDao
import com.example.lessons11.data.room.HistoryDataBase

class App:Application() {

    override fun onCreate() {
        super.onCreate()
    }

    companion object {
        private var appInstance:App? = null
        private var  db:HistoryDataBase? = null
        private const val DB_NAME = "History.db"

        fun getHistoryDao():HistoryDao{
            if(db == null){
                synchronized(HistoryDataBase::class.java){
                    if (db == null){
                        if (appInstance == null) throw IllegalStateException("Aplication is null while creating Database")
                        db = Room.databaseBuilder(
                            appInstance!!.applicationContext,
                            HistoryDataBase::class.java,
                            DB_NAME)
                            .allowMainThreadQueries()
                            .build()
                    }
                }
            }
            return db!!.historyDao()
        }
    }
}