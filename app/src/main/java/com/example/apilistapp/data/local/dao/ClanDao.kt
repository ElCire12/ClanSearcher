package com.example.apilistapp.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.apilistapp.data.local.entity.ClanEntity

@Dao
interface ClanDao {

    @Query("SELECT * FROM favorites")
    suspend fun getAllCharacters(): MutableList<ClanEntity>

    @Query("SELECT * FROM favorites WHERE tag = :tag")
    suspend fun getCharacterById(tag: Int): ClanEntity

    @Insert
    suspend fun addCharacter(clan: ClanEntity)

    @Delete
    suspend fun deleteCharacter(clan: ClanEntity)
}