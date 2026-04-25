package com.example.apilistapp.data.remote

import com.example.apilistapp.data.remote.dto.ClanInfo.ClanInfoDto
import com.example.apilistapp.data.remote.dto.ClansList.ClansListDto
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {

    @GET("v1/clans")
    suspend fun getClansList(
        @Header("Authorization") token: String,
        @Query("locationId") locationId: Int? = null,
        @Query("limit") limit: Int = 50,
        @Query("name") name: String? = null
    ): Response<ClansListDto>

    @GET("v1/clans/{clanTag}")
    suspend fun getClanInfo(
        @Header("Authorization") token: String,
        @Path("clanTag") clanTag: String
    ): Response<ClanInfoDto>

    companion object {
        const val BASE_URL = "https://api.clashofclans.com/"
        
        fun create(): ApiInterface {
            val client = OkHttpClient.Builder().build()
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
            return retrofit.create(ApiInterface::class.java)
        }
    }
}
