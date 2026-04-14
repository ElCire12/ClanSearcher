package com.example.apilistapp.data.remote.dto.ClanInfo

data class Member(
    val builderBaseLeague: com.example.apilistapp.data.remote.dto.ClanInfo.BuilderBaseLeague,
    val builderBaseTrophies: Int,
    val clanRank: Int,
    val donations: Int,
    val donationsReceived: Int,
    val expLevel: Int,
    val league: com.example.apilistapp.data.remote.dto.ClanInfo.League,
    val leagueTier: com.example.apilistapp.data.remote.dto.ClanInfo.LeagueTier,
    val name: String,
    val playerHouse: com.example.apilistapp.data.remote.dto.ClanInfo.PlayerHouse,
    val previousClanRank: Int,
    val role: String,
    val tag: String,
    val townHallLevel: Int,
    val trophies: Int
)