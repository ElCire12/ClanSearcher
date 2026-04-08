package com.example.apilistapp.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.example.apilistapp.ui.screens.favorites.FavoritesScreen
import com.example.apilistapp.ui.screens.list.ListScreen
import com.example.apilistapp.ui.screens.settings.SettingsScreen


@Composable
fun NavigationWrapper(){
    val backStack = rememberNavBackStack(Route.ListScreen)
    val currentRoute = backStack.lastOrNull()
    Scaffold(
        bottomBar = {
            NavigationBar {
                bottomBarItems.forEach { item ->
                    NavigationBarItem(
                        selected = (currentRoute == item.route),
                        onClick = {
                            if (currentRoute != item.route) {
                                backStack.clear()
                                backStack.add(item.route)
                            }
                        },
                        icon = { Icon(item.icon, contentDescription = item.label) },
                        label = { Text(item.label) }
                    )
                }
            }

        }
    ) { innerPadding ->
        NavDisplay(
            backStack = backStack,
            modifier = Modifier.padding(innerPadding),
            onBack = { backStack.removeLastOrNull() },
            entryProvider = entryProvider {
                entry<Route.ListScreen> { ListNavigation() }
                entry<Route.FavoritesScreen> { FavoritesScreen() }
                entry<Route.SettingsScreen> { SettingsScreen() }
            }
        )
    }
}
