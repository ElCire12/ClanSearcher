package com.example.apilistapp.ui.screens.detail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apilistapp.data.mapper.toDomain
import com.example.apilistapp.data.repository.ApiRepository
import com.example.apilistapp.data.repository.FavoriteRepository
import com.example.apilistapp.domain.ClanDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailScreenViewModel : ViewModel() {
    private val repository = ApiRepository()
    private val localRepository = FavoriteRepository()
    private val _clanInfo = MutableStateFlow<ClanDomain?>(null)
    val clanInfo: StateFlow<ClanDomain?> = _clanInfo.asStateFlow()

    fun getClanInfo(tag: String) {
        viewModelScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                val responseClan = repository.getClanInfo(tag)
                _clanInfo.value = responseClan
            }
        }
    }

    fun setClanInfoNull(){
        _clanInfo.value = null
    }

    fun addToFavorites(clan: ClanDomain) {
        viewModelScope.launch {
            localRepository.saveAsFavorite(clan)
        }
    }
}