package com.example.apilistapp.ui.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

sealed class Route: NavKey {
    @Serializable
    data object ListScreen: Route()

    @Serializable
    data object FavoritesScreen: Route()

    @Serializable
    data object SettingsScreen: Route()
}

sealed class ListNestedRoute: NavKey {
    @Serializable data object MainList: ListNestedRoute()
    @Serializable
    data class Detail(val clanTag: String) : ListNestedRoute()}

sealed class FavoriteNestedRoute: NavKey {
    @Serializable data object MainFavoriteList: ListNestedRoute()
    @Serializable
    data class FavoriteDetail(val clanTag: String) : ListNestedRoute()}