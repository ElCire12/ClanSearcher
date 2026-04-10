package com.example.apilistapp.data.repository

import com.example.apilistapp.data.remote.ApiInterface

class ApiRepository {
    val apiInterface = ApiInterface.create()
    suspend fun getClans() = apiInterface.getClans(locationId = 32000218)
    suspend fun searchClan(name: String?) = apiInterface.getClans(name = name, limit = 10)
}