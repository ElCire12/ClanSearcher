package com.example.apilistapp.ui.screens.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.example.apilistapp.data.repository.FavoriteRepository
import com.example.apilistapp.domain.Clan
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavoritesViewModel : ViewModel() {

    private val repository = FavoriteRepository()
    private val _favorites = MutableStateFlow<List<Clan>>(emptyList())
    val favorites: StateFlow<List<Clan>> = _favorites.asStateFlow()

    fun loadFavorites() {
        // Ejecutamos en una corrutina porque el acceso a datos puede tardar
        viewModelScope.launch(Dispatchers.IO) {
            val list = repository.getFavorites()
            withContext(Dispatchers.Main) {
                _favorites.value = list
            }
        }
    }

    fun searchFavorite(tag: String) {
        // Ejecutamos en una corrutina porque el acceso a datos puede tardar
        viewModelScope.launch(Dispatchers.IO) {
            val list = repository.searchByName(tag)
            withContext(Dispatchers.Main) {
                _favorites.value = list
            }
        }
    }
}