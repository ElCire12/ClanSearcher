package com.example.apilistapp.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarItem(val route: Route, val label: String, val icon: ImageVector) {
    data object List : BottomBarItem(Route.ListScreen, "Lista", Icons.AutoMirrored.Filled.List)
    data object Favorites : BottomBarItem(Route.FavoritesScreen, "Favoritos", Icons.Default.Favorite)
    data object Settings : BottomBarItem(Route.SettingsScreen, "Ajustes", Icons.Default.Settings)
}
val bottomBarItems = listOf(BottomBarItem.List, BottomBarItem.Favorites, BottomBarItem.Settings)