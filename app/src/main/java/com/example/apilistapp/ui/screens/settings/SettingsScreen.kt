package com.example.apilistapp.ui.screens.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.apilistapp.ui.components.DropDownComponent

@Composable
fun SettingsScreen(settings: SettingsViewModel) {
    // Instancia del ViewModel siguiendo el estilo de tu clase

    val isDarkMode by settings.isDarkMode.collectAsStateWithLifecycle()

    var selectedText by remember { mutableStateOf(if (settings.isGridMode.value) "Grid" else "List") }
    var gridDropDownExpanded by remember { mutableStateOf(false) }
    val gridOptions =
        listOf("List", "Grid")

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

                DropDownComponent(
                    selectedText = selectedText,
                    options = gridOptions,
                    expanded = gridDropDownExpanded,
                    onOptionSelected = { newValue ->
                        selectedText = newValue
                        settings.onLayoutChanged(newValue == "Grid")
                    },
                    onExpandedChange = { newValue ->
                        gridDropDownExpanded = newValue
                    }
                )
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