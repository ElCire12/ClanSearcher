package com.example.apilistapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.example.apilistapp.ui.screens.detail.DetailScreen
import com.example.apilistapp.ui.screens.favorites.FavoritesScreen
import com.example.apilistapp.ui.screens.settings.SettingsViewModel

@Composable
fun FavoriteNavigation(settings: SettingsViewModel) {
    val listBackStack = rememberNavBackStack(FavoriteNestedRoute.MainFavoriteList)

    NavDisplay(
        backStack = listBackStack,
        onBack = { listBackStack.removeLastOrNull() },
        entryProvider = entryProvider {

            entry<FavoriteNestedRoute.MainFavoriteList> {
                FavoritesScreen(
                    settings = settings,
                    navigateToDetail = { tagClicado ->
                        listBackStack.add(FavoriteNestedRoute.FavoriteDetail(tagClicado))
                    }
                )
            }

            entry<FavoriteNestedRoute.FavoriteDetail> { nestedRoute ->
                DetailScreen(
                    clanTag = nestedRoute.clanTag,
                    navigateBack = { listBackStack.removeLastOrNull() }
                )
            }
        }
    )
}