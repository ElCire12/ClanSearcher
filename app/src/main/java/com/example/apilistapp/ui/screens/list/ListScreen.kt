package com.example.apilistapp.ui.screens.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.apilistapp.domain.ClanDomain
import com.example.apilistapp.ui.components.ClanListComponent
import com.example.apilistapp.ui.components.LoadingComponent
import com.example.apilistapp.ui.components.SearchHeaderComponent

@Composable
fun ListScreen(navigateToDetail: (String) -> Unit) {
    var text by remember { mutableStateOf("") }
    val viewModel: ClansListViewModel = viewModel()
    val clans by viewModel.clans.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.getClansList()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8FAFC)) // Fondo Slate 50 (como en JS)
    ) {

        SearchHeaderComponent(
            title = "Explorar Clanes",
            query = text, // Usamos la variable 'text' que definimos arriba
            placeholder = "Buscar por nombre o tag...",
            onQueryChange = { nuevoTexto ->
                text = nuevoTexto // Actualiza el estado visual del TextField
                viewModel.searchClan(nuevoTexto) // Ejecuta la búsqueda en la API
            }
        )

        if (clans != emptyList<ClanDomain>()) {
            ClanListComponent(clans = clans, navigateToDetail = navigateToDetail)

        } else {
            LoadingComponent(message = "Obteniendo clanes...")
        }
    }
}

