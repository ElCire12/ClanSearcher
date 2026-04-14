package com.example.apilistapp.data.remote.dto.ClansList

import com.google.gson.annotations.SerializedName

data class ClansList(
    @SerializedName("items")
    val clans: List<Clan>,
    val paging: Paging
)