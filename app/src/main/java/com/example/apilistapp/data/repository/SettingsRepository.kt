package com.example.apilistapp.data.repository

import android.content.Context
import com.example.apilistapp.data.preferences.SettingsManager

class SettingsRepository(context: Context) {
    private val settingsManager = SettingsManager(context)

    fun isDarkModeEnabled(): Boolean {
        return settingsManager.getSettingValue("dark_mode", false)
    }

    fun setDarkMode(enabled: Boolean) {
        settingsManager.saveSettingValue("dark_mode", enabled)
    }
}
