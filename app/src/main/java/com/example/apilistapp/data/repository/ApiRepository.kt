package com.example.apilistapp.data.repository

import android.util.Log
import com.example.apilistapp.data.mapper.toDomain
import com.example.apilistapp.data.remote.ApiInterface
import com.example.apilistapp.domain.Clan
import retrofit2.Response

class ApiRepository {
    val apiInterface = ApiInterface.create()
    suspend fun getClans(): List<Clan> {
        val clansList: List<Clan>?
        val response = apiInterface.getClansList(locationId = 32000218)

        if (response.isSuccessful) {
            clansList = response.body()!!.clans.map { it.toDomain() }
        } else clansList = emptyList(); errorMessage(response)

        return clansList
    }

    suspend fun searchClan(name: String?): List<Clan> {
        val clansList: List<Clan>?
        val response = apiInterface.getClansList(name = name, limit = 10)

        if (response.isSuccessful) {
            clansList = response.body()!!.clans.map { it.toDomain() }
        } else clansList = emptyList(); errorMessage(response)

        return clansList
    }

    suspend fun getClanInfo(tag: String): Clan? {
        val clan: Clan?
        val response = apiInterface.getClanInfo(clanTag = tag)

        if (response.isSuccessful) {
            clan = response.body()!!.toDomain()
        } else clan = null; errorMessage(response)

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