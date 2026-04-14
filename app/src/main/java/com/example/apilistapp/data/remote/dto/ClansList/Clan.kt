package com.example.apilistapp.data.remote.dto.ClansList

data class Clan(
    val badgeUrls: com.example.apilistapp.data.remote.dto.ClansList.BadgeUrls,
    val capitalLeague: com.example.apilistapp.data.remote.dto.ClansList.CapitalLeague,
    val chatLanguage: com.example.apilistapp.data.remote.dto.ClansList.ChatLanguage,
    val clanBuilderBasePoints: Int,
    val clanCapitalPoints: Int,
    val clanLevel: Int,
    val clanPoints: Int,
    val isFamilyFriendly: Boolean,
    val isWarLogPublic: Boolean,
    val labels: List<com.example.apilistapp.data.remote.dto.ClansList.Label>,
    val location: com.example.apilistapp.data.remote.dto.ClansList.Location,
    val members: Int,
    val name: String,
    val requiredBuilderBaseTrophies: Int,
    val requiredTownhallLevel: Int,
    val requiredTrophies: Int,
    val tag: String,
    val type: String,
    val warFrequency: String,
    val warLeague: com.example.apilistapp.data.remote.dto.ClansList.WarLeague,
    val warLosses: Int,
    val warTies: Int,
    val warWinStreak: Int,
    val warWins: Int
)