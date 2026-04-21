package com.example.apilistapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.example.apilistapp.ui.screens.detail.DetailScreen
import com.example.apilistapp.ui.screens.favorites.FavoritesScreen

@Composable
fun FavoriteNavigation() {
    val listBackStack = rememberNavBackStack(FavoriteNestedRoute.MainFavoriteList)

    NavDisplay(
        backStack = listBackStack,
        onBack = { listBackStack.removeLastOrNull() },
        entryProvider = entryProvider {

            entry<FavoriteNestedRoute.MainFavoriteList> {
                FavoritesScreen(
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