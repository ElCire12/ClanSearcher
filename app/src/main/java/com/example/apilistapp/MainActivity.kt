package com.example.apilistapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.apilistapp.ui.navigation.NavigationWrapper
import com.example.apilistapp.ui.screens.settings.SettingsViewModel
import com.example.apilistapp.ui.theme.APIListAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val settingsVM = SettingsViewModel(this)
        enableEdgeToEdge()
        setContent {
            val darkModeActual by settingsVM.isDarkMode.collectAsStateWithLifecycle()

            APIListAppTheme(darkTheme = darkModeActual) {
                NavigationWrapper(settingsVM)
            }
        }
    }
}