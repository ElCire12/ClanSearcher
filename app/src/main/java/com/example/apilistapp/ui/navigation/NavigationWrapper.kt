package com.example.apilistapp.ui.navigation

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.rememberNavBackStack
import okhttp3.Route

@Composable
fun NavigationWrapper(){
    val backStack = rememberNavBackStack(Route.Pantalla1)
    val currentRoute = backStack.lastOrNull()
    Scaffold(
        bottomBar = {
            //TODO: Definició de NavigationBar
        }
    ) { innerPadding ->
        //TODO: Definició de NavDisplay
    }
}
