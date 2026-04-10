package com.example.apilistapp.ui.screens.list

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.apilistapp.domain.ClansList.Clan

@Composable
fun ListScreen(navigateToDetail: (String) -> Unit) {
    var text by remember { mutableStateOf("") }

    val viewModel: ClansListViewModel = viewModel()
    val clans by viewModel.clans.collectAsStateWithLifecycle()


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 30.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = text,
            onValueChange = { text = it; viewModel.searchClan(text) },
            label = { Text("Buscar:") },
            singleLine = true
        )

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(clans) { clan ->
                ClanItem(clan, navigateToDetail)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun ClanItem(clan: Clan, navigateToDetail: (String) -> Unit) {
    Card(
        border = BorderStroke(2.dp, Color.LightGray),
        modifier = Modifier.fillMaxWidth()
            .clickable { navigateToDetail(clan.tag) }
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 20.dp)
        ) {
            AsyncImage(
                model = clan.badgeUrls.large,
                contentDescription = "Imagen clan",
                modifier = Modifier.size(50.dp),
            )
            Spacer(Modifier.size(30.dp))
            Text(clan.name, fontSize = 28.sp, fontWeight = FontWeight.Bold)
        }
    }
}