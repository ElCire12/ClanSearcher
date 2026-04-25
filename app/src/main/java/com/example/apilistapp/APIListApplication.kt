package com.example.apilistapp

import android.app.Application
import androidx.room.Room
import com.example.apilistapp.data.local.AppDatabase
import com.example.apilistapp.data.repository.SettingsRepository


class APIListApplication : Application() {
    companion object {
        lateinit var database: AppDatabase
        lateinit var settingsRepository: SettingsRepository
    }

    override fun onCreate() {
        super.onCreate()
        database =
            Room.databaseBuilder(this, AppDatabase::class.java, "favorites").build()
        
        // Inicializamos el repositorio de ajustes aquí
        settingsRepository = SettingsRepository(this)
    }
}
