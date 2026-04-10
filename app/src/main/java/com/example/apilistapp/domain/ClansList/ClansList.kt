package com.example.apilistapp.domain.ClansList

import com.google.gson.annotations.SerializedName

data class ClansList(
    @SerializedName("items")
    val clans: List<Clan>,
    val paging: Paging
)