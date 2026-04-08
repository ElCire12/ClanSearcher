package com.example.apilistapp.data.mapper

import com.example.apilistapp.data.local.entity.SWCharacterEntity
import com.example.apilistapp.data.remote.dto.SWCharacterDto
import com.example.apilistapp.domain.SWCharacter

fun SWCharacterDto.toDomain(): SWCharacter {
    val id = url.trimEnd('/').split('/').last().toIntOrNull() ?: 0
    return SWCharacter(
        id = id,
        name = name,
        birthYear = birthYear,
        gender = gender,
        height = height.toIntOrNull() ?: 0,
        mass = mass,
        hairColor = hair_color,
        eyeColor = eye_color,
        homeworld = homeworld,
        films = films
    )
}

fun SWCharacter.toEntity(): SWCharacterEntity {
    return SWCharacterEntity(
        id = id,
        name = name,
        birthYear = birthYear,
        gender = gender,
        height = height,
        mass = mass,
        hairColor = hairColor,
        eyeColor = eyeColor,
        homeworld = homeworld,
        films = films.joinToString(",")
    )
}

fun SWCharacterEntity.toDomain(): SWCharacter {
    return SWCharacter(
        id = id,
        name = name,
        birthYear = birthYear,
        gender = gender,
        height = height,
        mass = mass,
        hairColor = hairColor,
        eyeColor = eyeColor,
        homeworld = homeworld,
        films = films.split(",").filter { it.isNotBlank() }
    )
}