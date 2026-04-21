package com.example.apilistapp.data.remote.dto.ClansList

import com.google.gson.annotations.SerializedName

data class ClansListDto(
    @SerializedName("items")
    val clans: List<ClanListItem>,
    val paging: Paging
)