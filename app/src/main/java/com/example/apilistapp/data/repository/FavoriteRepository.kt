package com.example.apilistapp.data.repository

import com.example.apilistapp.APIListApplication
import com.example.apilistapp.data.mapper.toDomain
import com.example.apilistapp.data.mapper.toEntity
import com.example.apilistapp.domain.Clan

class FavoriteRepository {
    val daoInterface = APIListApplication.database.clanDao()
    suspend fun saveAsFavorite(clan: Clan) = daoInterface.addCharacter(clan.toEntity())
    suspend fun deleteFavorite(character: Clan)= daoInterface.deleteCharacter(character.toEntity())

    suspend fun deleteAllFavorites() = daoInterface.deleteAllClans()

    suspend fun searchByName(tag: String): List<Clan> = daoInterface.searchByName(tag)?.map { it.toDomain() } ?: emptyList()

    suspend fun isFavorite(tag: String) = daoInterface.getCharacterByTag(tag) != null

    suspend fun getFavorites(): MutableList<Clan> {
        val favoritesList = mutableListOf<Clan>()
        daoInterface.getAllCharacters().forEach {
            favoritesList.add(it.toDomain())
        }
        return favoritesList
    }
}
