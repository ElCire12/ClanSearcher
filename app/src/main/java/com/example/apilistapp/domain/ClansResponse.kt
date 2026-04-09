package com.example.apilistapp.domain

import com.google.gson.annotations.SerializedName

data class ClansResponse(
    @SerializedName("items")
    val clans: List<Clan>,
    val paging: Paging
)