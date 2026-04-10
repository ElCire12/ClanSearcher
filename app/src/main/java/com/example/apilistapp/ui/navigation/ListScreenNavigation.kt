package com.example.apilistapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.example.apilistapp.ui.screens.detail.DetailScreen
import com.example.apilistapp.ui.screens.list.ListScreen

@Composable
fun ListNavigation() {
    val listBackStack = rememberNavBackStack(ListNestedRoute.MainList)

    NavDisplay(
        backStack = listBackStack,
        onBack = { listBackStack.removeLastOrNull() },
        entryProvider = entryProvider {

            entry<ListNestedRoute.MainList> {
                ListScreen(
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