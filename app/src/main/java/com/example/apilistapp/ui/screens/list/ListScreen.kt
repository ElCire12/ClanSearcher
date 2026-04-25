package com.example.apilistapp.ui.screens.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.apilistapp.ui.components.ClanGridComponent
import com.example.apilistapp.ui.components.ClanListComponent
import com.example.apilistapp.ui.components.LoadingComponent
import com.example.apilistapp.ui.components.SearchHeaderComponent
import com.example.apilistapp.ui.screens.settings.SettingsViewModel

@Composable
fun ListScreen(settings: SettingsViewModel, navigateToDetail: (String) -> Unit) {
    var text by remember { mutableStateOf("") }
    val viewModel: ClansListViewModel = viewModel()
    val clans by viewModel.clans.collectAsStateWithLifecycle()
    val isGrid by settings.isGridMode.collectAsStateWithLifecycle()
    val apiKey by settings.apiKey.collectAsStateWithLifecycle()

    LaunchedEffect(apiKey) {
        if (apiKey.isNotBlank()) {
            viewModel.getClansList()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {

        // HEADER
        SearchHeaderComponent(
            title = "Explorar Clanes",
            query = text,
            placeholder = "Buscar por nombre...",
            onQueryChange = { nuevoTexto ->
                text = nuevoTexto
                if (apiKey.isNotBlank()) {
                    viewModel.searchClan(nuevoTexto)
                }
            }
        )

        // CONTENIDO
        if (apiKey.isNotEmpty()) {
            if (isGrid)
                ClanGridComponent(clans = clans, navigateToDetail = navigateToDetail)
            else
                ClanListComponent(clans = clans, navigateToDetail = navigateToDetail)

        } else if (apiKey.isEmpty()) {
            AlertaApiKey()
        } else {
            LoadingComponent(message = "Obteniendo clanes...")
        }
    }
}

@Composable
fun AlertaApiKey() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(32.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Warning,
                contentDescription = null,
                modifier = Modifier.size(64.dp),
                tint = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Falta la API Key",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )
            Text(
                text = "Por favor, ve a Ajustes e introduce tu clave de Supercell para poder buscar clanes.",
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}
