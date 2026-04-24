package com.example.apilistapp.domain

data class Clan(
    val logoUrlLarge: String,
    val logoUrlSmall: String,
    val clanLevel: Int,
    val clanPoints: Int,
    val description: String?, //Cuando pedimos una lista no está la descripción
    val location: String?, //Cuando pedimos una lista a veces viene la location en null
    val memberList: List<Member>?, //Cuando pedimos una lista no contiene lista de miembros
    val members: Int,
    val name: String,
    val tag: String,
    val warWinStreak: Int,
    val warWins: Int
)
