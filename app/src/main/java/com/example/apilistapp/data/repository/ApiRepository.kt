package com.example.apilistapp.data.repository

import android.util.Log
import com.example.apilistapp.APIListApplication
import com.example.apilistapp.data.mapper.toDomain
import com.example.apilistapp.data.remote.ApiInterface
import com.example.apilistapp.data.remote.dto.ClansList.ClansListDto
import com.example.apilistapp.domain.Clan
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import retrofit2.Response

class ApiRepository {
    private val apiInterface = ApiInterface.create()
    private val settingsRepository = APIListApplication.settingsRepository

    private fun getAuthHeader(): String {
        val key = settingsRepository.getApiKey()
        return if (key.startsWith("Bearer ")) key else "Bearer $key"
    }

    suspend fun getClans(name: String = "", limit: Int = 10, location: Int = 32000218): List<Clan> {
        val clansList: List<Clan>?
        val response: Response<ClansListDto>
        val auth = getAuthHeader()

        //Si no se busca nada obtener 50 de españa
        if (name == ""){
            response = apiInterface.getClansList(token = auth, limit = 50, locationId = location)
        }
        else { //Si se busca algo obtener por el nombre, con un limite de 10
            response = apiInterface.getClansList(token = auth, name = name, limit = limit)
        }

        if (response.isSuccessful) {
            clansList = response.body()!!.clans.map { it.toDomain() }
        } else {
            clansList = emptyList()
            errorMessage(response)
        }

        return clansList
    }

    suspend fun getClanInfo(tag: String): Clan? {
        val clan: Clan?
        val auth = getAuthHeader()
        val response = apiInterface.getClanInfo(token = auth, clanTag = tag)

        if (response.isSuccessful) {
            clan = response.body()!!.toDomain()
        } else {
            clan = null
            errorMessage(response)
        }

        return clan
    }

    fun errorMessage(response: Response<*>) {
        val codigoError = response.code()
        val cuerpoError = response.errorBody()?.string()

        Log.e("MI_APP", "Código HTTP: $codigoError")
        Log.e("MI_APP", "Mensaje genérico: ${response.message()}")
        Log.e("MI_APP", "Detalle del error: $cuerpoError")
    }
}
