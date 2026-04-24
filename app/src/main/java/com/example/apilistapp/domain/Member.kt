package com.example.apilistapp.domain


data class Member(
    val builderBaseTrophies: Int,
    val clanRank: Int,
    val donations: Int,
    val donationsReceived: Int,
    val expLevel: Int,
    val name: String,
    val role: String,
    val tag: String,
    val townHallLevel: Int,
    val trophies: Int
)
