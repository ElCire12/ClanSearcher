package com.example.apilistapp.data.mapper

import com.example.apilistapp.data.remote.dto.ClanInfo.ClanInfoDto
import com.example.apilistapp.data.remote.dto.ClansList.ClanListItem
import com.example.apilistapp.domain.ClanDomain

fun ClanInfoDto.toDomain(): ClanDomain{
    return ClanDomain(
        logoUrlLarge = badgeUrls.large,
        logoUrlSmall = badgeUrls.small,
        clanLevel = clanLevel,
        clanPoints = clanPoints,
        description = description,
        location = location.name,
        memberList = memberList,
        members = members,
        name =  name,
        tag = tag,
        warWinStreak = warWinStreak,
        warWins = warWins
    )
}

fun ClanListItem.toDomain(): ClanDomain{
    return ClanDomain(
        logoUrlLarge = badgeUrls.large,
        logoUrlSmall = badgeUrls.small,
        clanLevel = clanLevel,
        clanPoints = clanPoints,
        description = null,
        location = location.name,
        memberList = null,
        members = members,
        name =  name,
        tag = tag,
        warWinStreak = warWinStreak,
        warWins = warWins
    )
}