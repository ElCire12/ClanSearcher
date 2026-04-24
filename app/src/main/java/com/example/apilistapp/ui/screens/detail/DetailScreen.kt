package com.example.apilistapp.ui.screens.detail

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.MilitaryTech
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.BookmarkAdd
import androidx.compose.material.icons.outlined.BookmarkAdded
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

import coil.compose.AsyncImage
import com.example.apilistapp.domain.Member
import com.example.apilistapp.ui.components.LoadingComponent

@Composable
fun DetailScreen(
    clanTag: String,
    navigateBack: () -> Unit
) {
    val viewModel: DetailScreenViewModel = viewModel()
    val clanInfo by viewModel.clanInfo.collectAsStateWithLifecycle()
    val isFavorite by viewModel.isFavorite.collectAsStateWithLifecycle()

    LaunchedEffect(clanTag) {
        viewModel.getClanInfo(clanTag)
        viewModel.checkIsFavorite(clanTag)
    }

    Box(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {
        val clanInfo =
            clanInfo //Copia de claninfo para evitar que clan info original se convierta en null en medio del pintado de este box

        if (clanInfo == null) {
            LoadingComponent(message = "Obteniendo datos del clan...")
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Cabecera: Botón Volver + Logo + Título
                item {
                    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            IconButton(onClick = navigateBack) {
                                Icon(
                                    Icons.AutoMirrored.Filled.ArrowBack,
                                    contentDescription = "Volver",
                                    tint = MaterialTheme.colorScheme.onBackground
                                )
                            }

                            IconButton(onClick = { viewModel.toggleFavorite(clanInfo!!) }) {
                                Icon(
                                    imageVector = if (isFavorite) Icons.Outlined.BookmarkAdded else Icons.Outlined.BookmarkAdd,
                                    contentDescription = "Guardar Clan",
                                    tint = if (isFavorite) Color(0xFFEAB308) else MaterialTheme.colorScheme.onBackground
                                )
                            }
                        }


                        Row(verticalAlignment = Alignment.CenterVertically) {
                            ClanLogo(imageUrl = clanInfo.logoUrlLarge)
                            Spacer(Modifier.width(16.dp))
                            Column {
                                Text(
                                    text = clanInfo.name.replace("\"", ""),
                                    style = MaterialTheme.typography.headlineMedium,
                                    fontWeight = FontWeight.ExtraBold,
                                    color = MaterialTheme.colorScheme.onBackground
                                )
                                Text(
                                    text = clanInfo.tag,
                                    style = MaterialTheme.typography.bodyLarge,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                    }
                }

                // Descripción del Clan
                item {
                    Card(
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
                        shape = RoundedCornerShape(12.dp),
                        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
                    ) {
                        Text(
                            text = clanInfo.description ?: "No hay descripción disponible",
                            modifier = Modifier.padding(16.dp),
                            style = MaterialTheme.typography.bodyMedium,
                            lineHeight = 20.sp,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }

                // Grid de estadísticas (2x2)
                item {
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            StatCard(
                                Modifier.weight(1f),
                                Icons.Default.Star,
                                "Puntos",
                                clanInfo.clanPoints.toString(),
                                Color(0xFFEAB308)
                            )
                            StatCard(
                                Modifier.weight(1f),
                                Icons.Default.EmojiEvents,
                                "Victorias en guerra",
                                clanInfo.warWins.toString(),
                                Color(0xFFA855F7)
                            )
                        }
                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            StatCard(
                                Modifier.weight(1f),
                                Icons.Default.LocationOn,
                                "Región",
                                clanInfo.location ?: "Internacional",
                                Color(0xFF10B981)
                            )
                            StatCard(
                                Modifier.weight(1f),
                                Icons.Default.MilitaryTech,
                                "Nivel",
                                clanInfo.clanLevel.toString(),
                                Color(0xFF3B82F6)
                            )
                        }
                    }
                }

                // Título Miembros
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Título principal
                        Text(
                            "Miembros (${clanInfo.members}/50)",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onBackground
                        )

                        // Leyenda de las flechas
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            // Leyenda Donadas
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    "Donaciones",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                                Icon(
                                    imageVector = Icons.Default.ArrowUpward, // O usa el texto "▲" para ser fiel al diseño
                                    contentDescription = null,
                                    modifier = Modifier.size(12.dp),
                                    tint = Color(0xFF059669)
                                )
                            }
                            // Leyenda Recibidas
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    "Recib.",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                                Icon(
                                    imageVector = Icons.Default.ArrowDownward, // O usa el texto "▼"
                                    contentDescription = null,
                                    modifier = Modifier.size(12.dp),
                                    tint = Color(0xFFE11D48)
                                )
                            }
                        }
                    }
                }

                // Lista de miembros (Equivalente a una tabla en JS)
                itemsIndexed(clanInfo.memberList ?: emptyList()) { index, member ->
                    MemberItem(member, index + 1)
                }
            }
        }
    }
}

@Composable
fun StatCard(modifier: Modifier, icon: ImageVector, label: String, value: String, color: Color) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
    ) {
        Row(Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
            Icon(icon, null, tint = color, modifier = Modifier.size(24.dp))
            Spacer(Modifier.width(8.dp))
            Column {
                Text(label, style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                Text(
                    value,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}

@Composable
fun MemberItem(member: Member, rank: Int) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
    ) {
        Row(Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
            Text(
                rank.toString(),
                Modifier.width(30.dp),
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontSize = 14.sp
            )
            Column(Modifier.weight(1f)) {
                Row( verticalAlignment = Alignment.CenterVertically,  horizontalArrangement = Arrangement.spacedBy(7.dp)) {
                    Text(member.name, fontWeight = FontWeight.Bold, fontSize = 15.sp, color = MaterialTheme.colorScheme.onSurface)
                    Text(
                        text = member.tag,
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                Text(
                    text = member.role.replaceFirstChar { it.uppercase() },
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            Column(horizontalAlignment = Alignment.End) {
                Text(
                    "${member.donations} ▲",
                    color = Color(0xFF059669),
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 11.sp
                )
                Text(
                    "${member.donationsReceived} ▼",
                    color = Color(0xFFE11D48),
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 11.sp
                )
            }
        }
    }
}

@Composable
fun ClanLogo(
    imageUrl: String?,
    modifier: Modifier = Modifier,
    size: Dp = 80.dp,
    backgroundColor: Color? = null,
    innerPadding: Dp = 10.dp
) {
    val finalBackgroundColor = backgroundColor ?: MaterialTheme.colorScheme.surfaceVariant
    Box(
        modifier = modifier
            .size(size)
            .clip(CircleShape)
            .background(finalBackgroundColor),
        contentAlignment = Alignment.Center
    ) {
        AsyncImage(
            model = imageUrl,
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentScale = ContentScale.Fit
        )
    }
}
