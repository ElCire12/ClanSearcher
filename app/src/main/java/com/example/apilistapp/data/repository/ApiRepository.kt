package com.example.apilistapp.data.repository

import com.example.apilistapp.data.mapper.toDomain
import com.example.apilistapp.data.remote.ApiInterface
import com.example.apilistapp.domain.ClanDomain

class ApiRepository {
    val apiInterface = ApiInterface.create()
    suspend fun getClans(): List<ClanDomain>? {
        val clansList: List<ClanDomain>?
        val response = apiInterface.getClansList(locationId = 32000218)

        if (response.isSuccessful) {
            clansList = response.body()!!.clans.map { it.toDomain() }
        } else clansList = null

        return clansList
    }

    suspend fun searchClan(name: String?): List<ClanDomain>? {
        val clansList: List<ClanDomain>?
        val response = apiInterface.getClansList(name = name, limit = 10)

        if (response.isSuccessful) {
            clansList = response.body()!!.clans.map { it.toDomain() }
        } else clansList = null

        return clansList
    }

    suspend fun getClanInfo(tag: String): ClanDomain? {
        val clan: ClanDomain?
        val response = apiInterface.getClanInfo(clanTag = tag)

        if (response.isSuccessful){
            clan = response.body()!!.toDomain()
        }
        else clan = null

        return clan
    }

    //fun errorMessage(response: Response)

}