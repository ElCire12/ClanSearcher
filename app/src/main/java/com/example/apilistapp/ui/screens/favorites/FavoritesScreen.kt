package com.example.apilistapp.ui.screens.favorites

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.apilistapp.ui.components.ClanGridComponent
import com.example.apilistapp.ui.components.ClanListComponent
import com.example.apilistapp.ui.components.SearchHeaderComponent
import com.example.apilistapp.ui.screens.settings.SettingsViewModel

@Composable
fun FavoritesScreen(settings: SettingsViewModel, navigateToDetail: (String) -> Unit) {

    val viewModel: FavoritesViewModel = viewModel()
    val favoriteClans by viewModel.favorites.collectAsStateWithLifecycle()
    val isGridMode by settings.isGridMode.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.loadFavorites()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8FAFC)) // Fondo Slate 50 (como en JS)
    ) {
//        SearchHeaderComponent(
//            title = "Explorar Clanes",
//            query = text, // Usamos la variable 'text' que definimos arriba
//            placeholder = "Buscar por nombre o tag...",
//            onQueryChange = { nuevoTexto ->
//                text = nuevoTexto // Actualiza el estado visual del TextField
//                viewModel.searchClan(nuevoTexto) // Ejecuta la búsqueda en la API
//            }
//        )

        if (isGridMode){
            ClanGridComponent(favoriteClans, navigateToDetail = navigateToDetail)
        }
        else ClanListComponent(favoriteClans, navigateToDetail = navigateToDetail)
    }
}