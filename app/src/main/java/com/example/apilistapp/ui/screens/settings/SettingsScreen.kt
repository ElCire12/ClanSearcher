package com.example.apilistapp.ui.screens.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
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
import com.example.apilistapp.ui.components.DropDownComponent

@Composable
fun SettingsScreen(settings: SettingsViewModel) {

    val isDarkMode by settings.isDarkMode.collectAsStateWithLifecycle()

    var selectedText by remember { mutableStateOf(if (settings.isGridMode.value) "Grid" else "List") }
    var gridDropDownExpanded by remember { mutableStateOf(false) }
    val gridOptions =
        listOf("List", "Grid")

    var showDeleteDialog by remember { mutableStateOf(false) }

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
            Button(onClick = { showDeleteDialog = true }) {
                Text("Delete Favs")
            }
        }

        if (showDeleteDialog) {
            AlertDialog(
                onDismissRequest = {
                    showDeleteDialog = false
                }, // Se cierra si el usuario pulsa fuera
                title = { Text(text = "Confirmar eliminación") },
                text = { Text("¿Estás seguro de que quieres borrar todos los clanes favoritos? Esta acción no se puede deshacer.") },
                confirmButton = {
                    Button(
                        onClick = {
                            settings.deleteAllFavorites() // Aquí es donde realmente borras
                            showDeleteDialog = false      // Y cierras el diálogo
                        }
                    ) {
                        Text("Eliminar todo")
                    }
                },
                dismissButton = {
                    Button(onClick = { showDeleteDialog = false }) {
                        Text("Cancelar")
                    }
                }
            )
        }
    }
}