package com.example.apilistapp.ui.screens.detail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apilistapp.data.mapper.toDomain
import com.example.apilistapp.data.repository.ApiRepository
import com.example.apilistapp.domain.ClanDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailScreenViewModel : ViewModel() {
    private val repository = ApiRepository()

    private val _clanInfo = MutableStateFlow<ClanDomain?>(null)
    val clanInfo: StateFlow<ClanDomain?> = _clanInfo.asStateFlow()

    fun getClanInfo(tag: String) {
        viewModelScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                val response = repository.getClanInfo(tag)
                if (response.isSuccessful) {
                    _clanInfo.value = response.body()?.toDomain()
                } else {
                    _clanInfo.value = null
                    val codigoError = response.code()
                    val cuerpoError = response.errorBody()?.string()

                    Log.e("MI_APP", "Código HTTP: $codigoError")
                    Log.e("MI_APP", "Mensaje genérico: ${response.message()}")
                    Log.e("MI_APP", "Detalle del error: $cuerpoError")
                }
            }
        }
        Log.d("MI_APP", "getclaninfo, tag: $tag, clan: ${_clanInfo.value}")
    }

    fun setClanInfoNull(){
        _clanInfo.value = null
    }
}