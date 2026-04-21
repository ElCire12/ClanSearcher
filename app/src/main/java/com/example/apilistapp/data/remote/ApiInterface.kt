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

    val TOKEN: String
        get() = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiIsImtpZCI6IjI4YTMxOGY3LTAwMDAtYTFlYi03ZmExLTJjNzQzM2M2Y2NhNSJ9.eyJpc3MiOiJzdXBlcmNlbGwiLCJhdWQiOiJzdXBlcmNlbGw6Z2FtZWFwaSIsImp0aSI6IjY2MzExNTdmLWQwNDMtNDQwMS1iMzFjLThmNmY3NWJjMzc5NiIsImlhdCI6MTc3Njc4Mzk0MCwic3ViIjoiZGV2ZWxvcGVyLzU4YmE3NWE3LTkwYjQtNjRiNS03YzZhLTgwY2NhNmM1MDdiNiIsInNjb3BlcyI6WyJjbGFzaCJdLCJsaW1pdHMiOlt7InRpZXIiOiJkZXZlbG9wZXIvc2lsdmVyIiwidHlwZSI6InRocm90dGxpbmcifSx7ImNpZHJzIjpbIjg1LjE5Mi43Mi4xOTciLCI3OS4xMTYuMTczLjY2IiwiNzkuMTU5LjIyNS4xNzUiXSwidHlwZSI6ImNsaWVudCJ9XX0.8dQwWS037MLR1Iz1ANeXwzjgaDqmqC35ffqJvy8NqAW8GEOpdtF__5b27msGNz3koF3mvIldyxkK2xrz8fvfAg"

    @GET("v1/clans")
    suspend fun getClansList(
        @Header("Authorization") token: String = TOKEN,

        @Query("locationId") locationId: Int? = null,

        @Query("limit") limit: Int = 50,

        @Query("name") name: String? = null

    ): Response<ClansListDto>

    @GET("v1/clans/{clanTag}")
    suspend fun getClanInfo(
        @Header("Authorization") token: String = TOKEN,

        @Path("clanTag") clanTag: String

    ): Response<ClanInfoDto>

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