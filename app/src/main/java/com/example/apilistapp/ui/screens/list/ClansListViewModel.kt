package com.example.apilistapp.ui.screens.list

import android.util.Log
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
    private val repository = ApiRepository()
    private val _clans = MutableStateFlow<List<Clan>>(emptyList())
    val clans: StateFlow<List<Clan>> = _clans.asStateFlow()

    init {
        getClans()
        Log.d("MI_APP", "INIT")
    }

    fun getClans() {
        Log.d("MI_APP", "getClans()")
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getClans()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    _clans.value = response.body()?.clans ?: emptyList()
                } else {
                    val codigoError = response.code()
                    val cuerpoError = response.errorBody()?.string()

                    Log.e("MI_APP", "Código HTTP: $codigoError")
                    Log.e("MI_APP", "Mensaje genérico: ${response.message()}")
                    Log.e("MI_APP", "Detalle del error: $cuerpoError")
                }
            }
        }
    }
}