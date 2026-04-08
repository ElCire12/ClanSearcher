package com.example.apilistapp.data.local.entity

//@Entity(tableName = "favorites")
data class SWCharacterEntity(
    //@PrimaryKey
    val id: Int,
    val name: String,
    val birthYear: String,
    val gender: String,
    val height: Int,
    val mass: String,
    val hairColor: String,
    val eyeColor: String,
    val homeworld: String,
    val films: String
)