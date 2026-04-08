package com.example.apilistapp.ui.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

sealed class Route: NavKey {
    @Serializable
    data object ListScreen: Route()

    @Serializable
    data object FavoritesScreen: Route()

    @Serializable
    data object DetailScreen: Route()
}
