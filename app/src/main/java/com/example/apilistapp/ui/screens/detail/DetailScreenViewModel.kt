package com.example.apilistapp.ui.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

    private val _isFavorite = MutableStateFlow(false)
    val isFavorite: StateFlow<Boolean> = _isFavorite.asStateFlow()

    fun toggleFavorite(clan: ClanDomain) {
        viewModelScope.launch(Dispatchers.IO) {
            if (_isFavorite.value) {
                localRepository.deleteFavorite(clan)
            } else {
                localRepository.saveAsFavorite(clan)
            }
            withContext(Dispatchers.Main) {
                _isFavorite.value = !_isFavorite.value
            }
        }
    }

    fun checkIsFavorite(tag: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val searchResult = localRepository.isFavorite(tag)

            withContext(Dispatchers.Main) {
                _isFavorite.value = searchResult
            }
        }
    }

    fun getClanInfo(tag: String) {
        _clanInfo.value = null
        viewModelScope.launch(Dispatchers.IO) {
            val responseClan = repository.getClanInfo(tag)
            withContext(Dispatchers.Main) {
                _clanInfo.value = responseClan
            }
        }
    }
}
