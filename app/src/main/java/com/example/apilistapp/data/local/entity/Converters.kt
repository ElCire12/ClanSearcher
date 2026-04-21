package com.example.apilistapp.data.local.entity

import androidx.room.TypeConverter
import com.example.apilistapp.domain.MemberDomain
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    private val gson = Gson()

    @TypeConverter
    fun fromMemberList(value: List<MemberDomain>?): String? {
        if (value == null) return null
        return gson.toJson(value)
    }

    @TypeConverter
    fun toMemberList(value: String?): List<MemberDomain>? {
        if (value == null) return null
        val listType = object : TypeToken<List<MemberDomain>>() {}.type
        return gson.fromJson(value, listType)
    }
}