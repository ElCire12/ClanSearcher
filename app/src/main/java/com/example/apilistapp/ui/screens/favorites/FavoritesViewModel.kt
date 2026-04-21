package com.example.apilistapp.ui.screens.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.example.apilistapp.data.repository.FavoriteRepository
import com.example.apilistapp.domain.ClanDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FavoritesViewModel: ViewModel() {

    private val repository = FavoriteRepository()
    private val _favorites = MutableStateFlow<List<ClanDomain>>(emptyList())
    val favorites: StateFlow<List<ClanDomain>> = _favorites.asStateFlow()


    fun loadFavorites() {
        // Ejecutamos en una corrutina porque el acceso a datos puede tardar
        viewModelScope.launch(Dispatchers.IO) {
            val list = repository.getFavorites()
            _favorites.value = list
        }
    }
}