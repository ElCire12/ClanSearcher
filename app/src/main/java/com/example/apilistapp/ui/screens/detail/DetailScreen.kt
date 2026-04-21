package com.example.apilistapp.ui.screens.detail

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage

@Composable
fun DetailScreen(
    clanTag: String, navigateBack: () -> Unit
) {
    val viewModel: DetailScreenViewModel = viewModel()
    val clan by viewModel.clanInfo.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.getClanInfo(clanTag)
        Log.d("MI_APP", "Clan: $clan")
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (clan == null) {
            CargandoInformacion()
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    // Añade esta línea para ver los bordes:
                    .border(width = 2.dp, color = Color.Red, shape = RoundedCornerShape(4.dp))
            ) {
                ClanLogo(imageUrl = clan!!.badgeUrls.large)
                Text(clan!!.name + " " + clan!!.tag)
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = { /*viewModel.setClanInfoNull();*/ navigateBack() }) {
                    Text("Volver a la Lista")
                }
            }

        }
    }


}

@Composable
fun CargandoInformacion() {
    CircularProgressIndicator()

    Spacer(Modifier.size(30.dp))

    Text("Cargando información del clan...")
}

@Composable
fun ClanLogo(
    imageUrl: String?,
    modifier: Modifier = Modifier,
    size: Dp = 80.dp,
    backgroundColor: Color = Color(0xFFEEF2FF), // Azul suave por defecto
    innerPadding: Dp = 12.dp
) {
    Box(
        modifier = modifier
            .size(size)
            .clip(CircleShape)
            .background(backgroundColor),
        contentAlignment = Alignment.Center
    ) {
        AsyncImage(
            model = imageUrl,
            contentDescription = "Clan Badge",
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentScale = ContentScale.Fit
        )
    }
}