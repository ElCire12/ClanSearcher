package com.example.apilistapp.ui.screens.settings

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.Button
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun SettingsScreen(settings: SettingsViewModel) {
    // Instancia del ViewModel siguiendo el estilo de tu clase

    val isDarkMode by settings.isDarkMode.collectAsStateWithLifecycle()

    Column(modifier = Modifier.fillMaxSize()) {
        Column(
            Modifier
                .fillMaxHeight(0.5f)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("Dark Mode")
                Switch(checked = isDarkMode, onCheckedChange = { newValue ->
                    settings.themeToggle(newValue)
                })
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("Show Mode")
                Switch(checked = true, onCheckedChange = {})
            }
        }
        Column(
            Modifier
                .fillMaxHeight()
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            Button(onClick = { settings.deleteAllFavorites() }) {
                Text("Delete Favs")
            }
        }
    }
}