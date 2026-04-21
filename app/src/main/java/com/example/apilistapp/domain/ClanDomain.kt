package com.example.apilistapp.domain

import Member


data class ClanDomain(
    val logoUrlLarge: String,
    val logoUrlSmall: String,
    val clanLevel: Int,
    val clanPoints: Int,
    val description: String?,
    val location: String,
    val memberList: List<Member>?,
    val members: Int,
    val name: String,
    val tag: String,
    val warWinStreak: Int,
    val warWins: Int
)
