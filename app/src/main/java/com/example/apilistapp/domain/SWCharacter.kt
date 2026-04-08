package com.example.apilistapp.domain

data class SWCharacter(
    val id: Int,
    val name: String,
    val birthYear: String,
    val gender: String,
    val height: Int,
    val mass: String,
    val hairColor: String,
    val eyeColor: String,
    val homeworld: String,
    val films: List<String>
)