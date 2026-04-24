package com.example.apilistapp.ui.screens.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apilistapp.data.repository.ApiRepository
import com.example.apilistapp.domain.Clan
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ClansListViewModel : ViewModel() {
    private val apiRepository = ApiRepository()
    private val _clans = MutableStateFlow<List<Clan>>(emptyList())
    val clans: StateFlow<List<Clan>> = _clans.asStateFlow()

    fun getClansList() {
        viewModelScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) {
               _clans.value = apiRepository.getClans()
            }
        }
    }

    fun searchClan(name: String) {
        viewModelScope.launch(Dispatchers.IO) {

            val responseList: List<Clan> = apiRepository.getClans(name)

            withContext(Dispatchers.Main) {
                _clans.value = responseList
            }
        }
    }
}