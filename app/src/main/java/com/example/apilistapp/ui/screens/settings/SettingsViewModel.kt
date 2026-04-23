package com.example.apilistapp.ui.screens.settings

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apilistapp.data.repository.FavoriteRepository
import com.example.apilistapp.data.repository.SettingsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SettingsViewModel(context: Context) : ViewModel() {
    private val repository = SettingsRepository(context)
    private val _isDarkMode = MutableStateFlow<Boolean>(repository.isDarkModeEnabled())
    val isDarkMode: StateFlow<Boolean> = _isDarkMode

    private val _isGridMode = MutableStateFlow<Boolean>(repository.isGridModeEnabled())
    val isGridMode: StateFlow<Boolean> = _isGridMode

    private val localRepository = FavoriteRepository()

    fun themeToggle(enabled: Boolean) {
        repository.setDarkMode(enabled)
        _isDarkMode.value = enabled
    }

    fun onLayoutChanged(isGrid: Boolean) {
        repository.setGridMode(isGrid)
        _isGridMode.value = isGrid
    }

    fun deleteAllFavorites() {
        viewModelScope.launch(Dispatchers.IO) {
            localRepository.deleteAllFavorites()
        }
    }
}