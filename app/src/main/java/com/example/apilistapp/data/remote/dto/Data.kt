package com.example.apilistapp.data.remote.dto

data class Data(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<SWCharacterDto>
)