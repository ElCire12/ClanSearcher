package com.example.apilistapp.ui.screens.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.apilistapp.domain.ClanDomain
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

    LaunchedEffect(Unit) {
        viewModel.getClansList()
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
                viewModel.searchClan(nuevoTexto)
            }
        )

        // LISTA
        if (clans != emptyList<ClanDomain>()) {
            if (isGrid)
                ClanGridComponent(clans = clans, navigateToDetail = navigateToDetail)
            else
                ClanListComponent(clans = clans, navigateToDetail = navigateToDetail)

        } else {
            LoadingComponent(message = "Obteniendo clanes...")
        }
    }
}
