package com.example.apilistapp.ui.screens.settings

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Brightness4
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.GridView
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.apilistapp.ui.components.DropDownComponent

@Composable
fun SettingsScreen(settings: SettingsViewModel) {

    val isDarkMode by settings.isDarkMode.collectAsStateWithLifecycle()
    val isGridMode by settings.isGridMode.collectAsStateWithLifecycle()

    var selectedText by remember { mutableStateOf(if (isGridMode) "Grid" else "List") }
    var gridDropDownExpanded by remember { mutableStateOf(false) }
    val gridOptions = listOf("List", "Grid")

    var showDeleteDialog by remember { mutableStateOf(false) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp, vertical = 24.dp)
        ) {
            // GRUPO APARIENCIA
            Text(
                text = "Apariencia",
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Card(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f)
                )
            ) {
                Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                    SettingsItem(
                        title = "Modo Oscuro",
                        icon = Icons.Default.Brightness4,
                        control = {
                            Switch(
                                checked = isDarkMode,
                                onCheckedChange = { settings.themeToggle(it) }
                            )
                        }
                    )
                    HorizontalDivider(thickness = 0.5.dp, color = MaterialTheme.colorScheme.outlineVariant)
                    SettingsItem(
                        title = "Vista",
                        icon = Icons.Default.GridView,
                        control = {
                            DropDownComponent(
                                selectedText = selectedText,
                                options = gridOptions,
                                expanded = gridDropDownExpanded,
                                onOptionSelected = { newValue ->
                                    selectedText = newValue
                                    settings.onLayoutChanged(newValue == "Grid")
                                },
                                onExpandedChange = { gridDropDownExpanded = it }
                            )
                        }
                    )
                }
            }

            // ESTE SPACER EMPUJA LO SIGUIENTE HACIA ABAJO
            Spacer(modifier = Modifier.weight(1f))

            // GRUPO DATOS
            Text(
                text = "Gestión de datos",
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.error,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Button(
                onClick = { showDeleteDialog = true },
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.medium,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.errorContainer,
                    contentColor = MaterialTheme.colorScheme.error
                ),
                contentPadding = PaddingValues(16.dp)
            ) {
                Icon(Icons.Default.Delete, contentDescription = null)
                Spacer(Modifier.width(8.dp))
                Text("Borrar todos los favoritos", fontWeight = FontWeight.Bold)
            }
        }

        if (showDeleteDialog) {
            DeleteConfirmDialog(
                onConfirm = {
                    settings.deleteAllFavorites()
                    showDeleteDialog = false
                },
                onDismiss = { showDeleteDialog = false }
            )
        }
    }
}

@Composable
fun SettingsItem(
    title: String,
    icon: ImageVector,
    control: @Composable () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
        control()
    }
}

@Composable
fun DeleteConfirmDialog(onConfirm: () -> Unit, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("¿Confirmar eliminación?") },
        text = { Text("¿Estás seguro de que quieres borrar todos los clanes favoritos? Esta acción no se puede deshacer.") },
        confirmButton = {
            TextButton(onClick = onConfirm) {
                Text("Eliminar todo", color = MaterialTheme.colorScheme.error, fontWeight = FontWeight.Bold)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancelar")
            }
        }
    )
}
