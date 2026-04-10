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
        @Header("Authorization") token: String = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiIsImtpZCI6IjI4YTMxOGY3LTAwMDAtYTFlYi03ZmExLTJjNzQzM2M2Y2NhNSJ9.eyJpc3MiOiJzdXBlcmNlbGwiLCJhdWQiOiJzdXBlcmNlbGw6Z2FtZWFwaSIsImp0aSI6IjY5YWY2MjIyLTdiZTgtNDIwMS04Mzc1LTIxYjRhYWY5NWNiOSIsImlhdCI6MTc3NTYzMDU4OSwic3ViIjoiZGV2ZWxvcGVyLzU4YmE3NWE3LTkwYjQtNjRiNS03YzZhLTgwY2NhNmM1MDdiNiIsInNjb3BlcyI6WyJjbGFzaCJdLCJsaW1pdHMiOlt7InRpZXIiOiJkZXZlbG9wZXIvc2lsdmVyIiwidHlwZSI6InRocm90dGxpbmcifSx7ImNpZHJzIjpbIjc5LjExNi4xNzMuNjYiXSwidHlwZSI6ImNsaWVudCJ9XX0.2o4324ASVJF_pAAE3ruirvHU8_GJ_2Pi7rUzFmytBPK_Mh6hs6OoD2aR4tCSgcsfFPkqr15ltwoDFR2u6jTp2g",

        @Query("locationId") locationId: Int? = null,

        @Query("limit") limit: Int = 50,

        @Query("name") name: String? = null

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