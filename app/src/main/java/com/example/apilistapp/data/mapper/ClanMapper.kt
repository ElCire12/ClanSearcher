package com.example.apilistapp.data.mapper

import MemberDto
import com.example.apilistapp.data.local.entity.ClanEntity
import com.example.apilistapp.data.remote.dto.ClanInfo.ClanInfoDto
import com.example.apilistapp.data.remote.dto.ClansList.ClanListItem
import com.example.apilistapp.domain.ClanDomain
import com.example.apilistapp.domain.MemberDomain

// CLANS MAPPER
//ClanInfo o ClanDomain
fun ClanInfoDto.toDomain(): ClanDomain {
    return ClanDomain(
        logoUrlLarge = badgeUrls.large,
        logoUrlSmall = badgeUrls.small,
        clanLevel = clanLevel,
        clanPoints = clanPoints,
        description = description,
        location = location.name,
        memberList = memberList.map { it.toDomain() },
        members = members,
        name = name,
        tag = tag,
        warWinStreak = warWinStreak,
        warWins = warWins
    )
}

//ListItem to ClanDomain
fun ClanListItem.toDomain(): ClanDomain {
    return ClanDomain(
        logoUrlLarge = badgeUrls.large,
        logoUrlSmall = badgeUrls.small,
        clanLevel = clanLevel,
        clanPoints = clanPoints,
        description = null, //Cuando pedimos una lista no está la descripción
        location = location?.name, //Cuando pedimos una lista a veces viene la location en null
        memberList = null, //Cuando pedimos una lista no contiene lista de miembros
        members = members,
        name = name,
        tag = tag,
        warWinStreak = warWinStreak,
        warWins = warWins
    )
}

// ClanDomain to Entity (DB)
fun ClanDomain.toEntity(): ClanEntity {
    return ClanEntity(
        tag = tag,
        logoUrlLarge = logoUrlLarge,
        logoUrlSmall = logoUrlSmall,
        clanLevel = clanLevel,
        clanPoints = clanPoints,
        description = description,
        location = location,
        memberList = memberList,
        members = members,
        name = name,
        warWinStreak = warWinStreak,
        warWins = warWins
    )
}

fun ClanEntity.toDomain(): ClanDomain {
    return ClanDomain(
        tag = tag,
        logoUrlLarge = logoUrlLarge,
        logoUrlSmall = logoUrlSmall,
        clanLevel = clanLevel,
        clanPoints = clanPoints,
        description = description,
        location = location,
        memberList = memberList,
        members = members,
        name = name,
        warWinStreak = warWinStreak,
        warWins = warWins
    )
}

// Member mapper
fun MemberDto.toDomain(): MemberDomain {
    return MemberDomain(
        builderBaseTrophies = builderBaseTrophies,
        clanRank = clanRank,
        donations = donations,
        donationsReceived = donationsReceived,
        expLevel = expLevel,
        name = name,
        role = role,
        tag = tag,
        townHallLevel = townHallLevel,
        trophies = trophies
    )
}