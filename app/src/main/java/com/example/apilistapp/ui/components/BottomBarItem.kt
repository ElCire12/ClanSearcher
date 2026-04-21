package com.example.apilistapp.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.apilistapp.ui.navigation.Route

sealed class BottomBarItem(val route: Route, val label: String, val icon: ImageVector) {
    data object ListScreen : BottomBarItem(Route.ListScreen, "Lista", Icons.AutoMirrored.Filled.List)
    data object Favorites : BottomBarItem(Route.FavoritesScreen, "Favoritos", Icons.Default.Favorite)
    data object Settings : BottomBarItem(Route.SettingsScreen, "Ajustes", Icons.Default.Settings)
}
val bottomBarItems = listOf(BottomBarItem.ListScreen, BottomBarItem.Favorites, BottomBarItem.Settings)