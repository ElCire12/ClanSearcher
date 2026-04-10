package com.example.apilistapp.domain.ClanInfo

data class Member(
    val builderBaseLeague: BuilderBaseLeague,
    val builderBaseTrophies: Int,
    val clanRank: Int,
    val donations: Int,
    val donationsReceived: Int,
    val expLevel: Int,
    val league: League,
    val leagueTier: LeagueTier,
    val name: String,
    val playerHouse: PlayerHouse,
    val previousClanRank: Int,
    val role: String,
    val tag: String,
    val townHallLevel: Int,
    val trophies: Int
)