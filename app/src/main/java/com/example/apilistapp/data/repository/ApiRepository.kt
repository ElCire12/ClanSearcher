package com.example.apilistapp.data.repository

import com.example.apilistapp.data.remote.ApiInterface

class ApiRepository {
    val apiInterface = ApiInterface.create()
    suspend fun getClans() = apiInterface.getClansList(locationId = 32000218)
    suspend fun searchClan(name: String?) = apiInterface.getClansList(name = name, limit = 10)
    suspend fun getClanInfo(tag: String) = apiInterface.getClanInfo(clanTag = tag)
}