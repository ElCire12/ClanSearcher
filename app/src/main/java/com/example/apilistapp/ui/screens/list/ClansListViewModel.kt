package com.example.apilistapp.ui.screens.list

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apilistapp.data.mapper.toDomain
import com.example.apilistapp.data.remote.dto.ClansList.ClansListDto
import com.example.apilistapp.data.repository.ApiRepository
import com.example.apilistapp.domain.ClanDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class ClansListViewModel : ViewModel() {
    private val repository = ApiRepository()
    private val _clans = MutableStateFlow<List<ClanDomain>>(emptyList())
    val clans: StateFlow<List<ClanDomain>> = _clans.asStateFlow()

    fun getClansList() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getClans()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    _clans.value = response.body()?.clans?.map { Log.d("MI_APP", it.location.toString()); it.toDomain() } ?: emptyList()
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

    fun searchClan(name: String) {
        viewModelScope.launch(Dispatchers.IO) {

            val response: Response<ClansListDto>

            if (name == "") {
                response = repository.getClans()
            } else {
                response = repository.searchClan(name)
            }

            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    _clans.value = (response.body()?.clans?.map {Log.d("MI_APP", "${it.location ?: null}"); it.toDomain() } ?: emptyList())
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