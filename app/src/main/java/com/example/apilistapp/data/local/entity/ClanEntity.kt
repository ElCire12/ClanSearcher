package com.example.apilistapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.apilistapp.domain.Member

@Entity(tableName = "favorites")
data class ClanEntity(
    @PrimaryKey
    val tag: String,
    val logoUrlLarge: String,
    val logoUrlSmall: String,
    val clanLevel: Int,
    val clanPoints: Int,
    val description: String?, //Cuando pedimos una lista no está la descripción
    val location: String?, //Cuando pedimos una lista a veces viene la location en null
    val memberList: List<Member>?, //Cuando pedimos una lista no contiene lista de miembros
    val members: Int,
    val name: String,
    val warWinStreak: Int,
    val warWins: Int
)