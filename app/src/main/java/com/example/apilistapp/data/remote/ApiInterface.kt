package com.example.apilistapp.data.remote

import com.example.apilistapp.domain.ClansResponse
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ApiInterface {
    @GET("v1/clans")
    suspend fun getClans(
        // La API de Clash of Clans OBLIGA a pasar tu Token de desarrollador
        @Header("Authorization") token: String,

        @Query("locationId") locationId: Int = 32000218,

        @Query("limit") limit: Int = 50

    ): Response<ClansResponse>

    companion object {
        const val BASE_URL = "https://api.clashofclans.com/"
        fun create(): ApiInterface {
            val client = OkHttpClient.Builder().build()
            val retrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(
                GsonConverterFactory.create()
            ).client(client).build()
            return retrofit.create(ApiInterface::class.java)
        }
    }
}