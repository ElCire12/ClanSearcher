package com.example.apilistapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.example.apilistapp.ui.screens.detail.DetailScreen
import com.example.apilistapp.ui.screens.list.ListScreen
import com.example.apilistapp.ui.screens.settings.SettingsViewModel

@Composable
fun ListNavigation(settings: SettingsViewModel) {
    val listBackStack = rememberNavBackStack(ListNestedRoute.MainList)

    NavDisplay(
        backStack = listBackStack,
        onBack = { listBackStack.removeLastOrNull() },
        entryProvider = entryProvider {

            entry<ListNestedRoute.MainList> {
                ListScreen(
                    settings = settings,
                    navigateToDetail = { tagClicado ->
                        listBackStack.add(ListNestedRoute.Detail(tagClicado))
                    }
                )
            }

            entry<ListNestedRoute.Detail> { nestedRoute ->
                DetailScreen(
                    clanTag = nestedRoute.clanTag,
                    navigateBack = { listBackStack.removeLastOrNull() }
                )
            }
        }
    )
}

