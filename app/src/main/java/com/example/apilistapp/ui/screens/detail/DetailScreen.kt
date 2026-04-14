package com.example.apilistapp.ui.screens.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.apilistapp.data.remote.dto.ClansList.Clan
import com.example.apilistapp.ui.screens.list.ClansListViewModel

@Composable
fun DetailScreen(
    clanTag: String, navigateBack: () -> Unit
) {
    val viewModel: DetailScreenViewModel = viewModel()
    val clan by viewModel.clanInfo.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.getClanInfo(clanTag)
    }

    if (clan == null) {
        // Muestras un texto de "Cargando..." o un CircularProgressIndicator
        Text("Cargando información del clan...")
    } else {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(clan!!.name + " " + clan!!.tag)
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { navigateBack() }) {
                Text("Volver a la Lista")
            }
        }
    }
}