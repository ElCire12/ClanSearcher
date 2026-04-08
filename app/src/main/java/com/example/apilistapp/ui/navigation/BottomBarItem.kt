package com.example.apilistapp.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.List
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarItem(val route: Route, val label: String, val icon: ImageVector) {
    data object Item1 : BottomBarItem(Route.ListScreen, "Lista", Icons.AutoMirrored.Filled.List)
    data object Item2 : BottomBarItem(Route.FavoritesScreen, "Favoritos", Icons.Default.Favorite)
    data object Item3 : BottomBarItem(Route.DetailScreen, "Detalle", Icons.Default.Settings)
}
val bottomBarItems = listOf(BottomBarItem.Item1, BottomBarItem.Item2, BottomBarItem.Item3)