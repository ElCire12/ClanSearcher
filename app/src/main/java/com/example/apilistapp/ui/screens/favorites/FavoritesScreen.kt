package com.example.apilistapp.ui.screens.favorites

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
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
    var searchText by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        viewModel.loadFavorites()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8FAFC)) // Fondo Slate 50 (como en JS)
    ) {
        SearchHeaderComponent(
            title = "Favoritos",
            query = searchText,
            placeholder = "Buscar por nombre...",
            onQueryChange = { nuevoTexto ->
                searchText = nuevoTexto
                viewModel.searchFavorite(nuevoTexto)
            }
        )

        if (favoriteClans.isEmpty()) {
            Column(
                modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text("No hay clanes favoritos", fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.secondary)
            }
        }

        if (isGridMode) {
            ClanGridComponent(favoriteClans, navigateToDetail = navigateToDetail)
        } else ClanListComponent(favoriteClans, navigateToDetail = navigateToDetail)
    }
}