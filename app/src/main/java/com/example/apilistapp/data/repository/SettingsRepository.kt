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

    // En SettingsRepository
    fun isGridModeEnabled(): Boolean {
        return settingsManager.getSettingValue("is_grid", false) // false por defecto (Lista)
    }

    fun setGridMode(enabled: Boolean) {
        settingsManager.saveSettingValue("is_grid", enabled)
    }


    fun getApiKey(): String {
        return settingsManager.getSettingValue("api_key", "")
    }

    fun setApiKey(key: String) {
        settingsManager.saveSettingValue("api_key", key)
    }
}
