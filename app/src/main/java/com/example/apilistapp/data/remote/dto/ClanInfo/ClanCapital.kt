package com.example.apilistapp.data.remote.dto.ClanInfo

data class ClanCapital(
    val capitalHallLevel: Int,
    val districts: List<com.example.apilistapp.data.remote.dto.ClanInfo.District>
)