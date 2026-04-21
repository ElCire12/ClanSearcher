package com.example.apilistapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.apilistapp.ui.navigation.NavigationWrapper
import com.example.apilistapp.ui.theme.APIListAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            APIListAppTheme {
                NavigationWrapper()
            }
        }
    }
}