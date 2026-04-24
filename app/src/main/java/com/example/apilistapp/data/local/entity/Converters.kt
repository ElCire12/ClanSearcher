package com.example.apilistapp.data.local.entity

import androidx.room.TypeConverter
import com.example.apilistapp.domain.Member
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    private val gson = Gson()

    @TypeConverter
    fun fromMemberList(value: List<Member>?): String? {
        if (value == null) return null
        return gson.toJson(value)
    }

    @TypeConverter
    fun toMemberList(value: String?): List<Member>? {
        if (value == null) return null
        val listType = object : TypeToken<List<Member>>() {}.type
        return gson.fromJson(value, listType)
    }
}