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
        //ITB
        get() = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiIsImtpZCI6IjI4YTMxOGY3LTAwMDAtYTFlYi03ZmExLTJjNzQzM2M2Y2NhNSJ9.eyJpc3MiOiJzdXBlcmNlbGwiLCJhdWQiOiJzdXBlcmNlbGw6Z2FtZWFwaSIsImp0aSI6ImI4OGI3ZDIwLTE1OTItNDIyYS05ZDhlLTJiOGUxZmQwYjNiOSIsImlhdCI6MTc3NjkyNTgxMiwic3ViIjoiZGV2ZWxvcGVyLzU4YmE3NWE3LTkwYjQtNjRiNS03YzZhLTgwY2NhNmM1MDdiNiIsInNjb3BlcyI6WyJjbGFzaCJdLCJsaW1pdHMiOlt7InRpZXIiOiJkZXZlbG9wZXIvc2lsdmVyIiwidHlwZSI6InRocm90dGxpbmcifSx7ImNpZHJzIjpbIjc5LjExNi4xNzMuNjYiLCI4NS4xOTIuNzIuMTk3Il0sInR5cGUiOiJjbGllbnQifV19.lCPc5syiKGyjMruMDLyjHnNNaTBfIYJZjS-cGrJ9I0JclFQTZR9nO4uUSgFWdSDdBlaxdvs9UiXMEODcJEgikQ"
        //Casa:
        //get() = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiIsImtpZCI6IjI4YTMxOGY3LTAwMDAtYTFlYi03ZmExLTJjNzQzM2M2Y2NhNSJ9.eyJpc3MiOiJzdXBlcmNlbGwiLCJhdWQiOiJzdXBlcmNlbGw6Z2FtZWFwaSIsImp0aSI6Ijk5ZmZhNGMyLTkwOWUtNGFmNS04NTJlLTFhNTQ0Nzg2YTdhOSIsImlhdCI6MTc3Njk3OTAzNCwic3ViIjoiZGV2ZWxvcGVyLzU4YmE3NWE3LTkwYjQtNjRiNS03YzZhLTgwY2NhNmM1MDdiNiIsInNjb3BlcyI6WyJjbGFzaCJdLCJsaW1pdHMiOlt7InRpZXIiOiJkZXZlbG9wZXIvc2lsdmVyIiwidHlwZSI6InRocm90dGxpbmcifSx7ImNpZHJzIjpbIjgzLjQ0LjM3LjExOSJdLCJ0eXBlIjoiY2xpZW50In1dfQ.7PB0JrVDlTK-aI__uQTMmTztmk0_C6dwYD5xvKeAjtzZ6VVV8wpN6hzTu8iJuMDx178Q9l8KPE11pNfRVqhmpg"

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
        /*
                const val BASE_URL = "https://api.clashofclans.com/"
                fun create(): ApiInterface {
                    val client = OkHttpClient.Builder().build()
                    val retrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(
                        GsonConverterFactory.create()
                    ).client(client).build()
                    return retrofit.create(ApiInterface::class.java)
                }*/
        const val BASE_URL = "https://api.clashofclans.com/"
        fun create(): ApiInterface {
            // 1. Crear el interceptor de logs
            val logging = okhttp3.logging.HttpLoggingInterceptor().apply {
                level = okhttp3.logging.HttpLoggingInterceptor.Level.BODY
            }

            // 2. Añadir el interceptor al cliente OkHttp
            val client = OkHttpClient.Builder()
                .addInterceptor(logging) // <--- Esta es la clave
                .build()

            // 3. Configurar Retrofit con ese cliente
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()

            return retrofit.create(ApiInterface::class.java)
        }
    }
}