package com.example.apilistapp.domain

data class Clan(
    val badgeUrls: BadgeUrls,
    val capitalLeague: CapitalLeague,
    val chatLanguage: ChatLanguage,
    val clanBuilderBasePoints: Int,
    val clanCapitalPoints: Int,
    val clanLevel: Int,
    val clanPoints: Int,
    val isFamilyFriendly: Boolean,
    val isWarLogPublic: Boolean,
    val labels: List<Label>,
    val location: Location,
    val members: Int,
    val name: String,
    val requiredBuilderBaseTrophies: Int,
    val requiredTownhallLevel: Int,
    val requiredTrophies: Int,
    val tag: String,
    val type: String,
    val warFrequency: String,
    val warLeague: WarLeague,
    val warLosses: Int,
    val warTies: Int,
    val warWinStreak: Int,
    val warWins: Int
)