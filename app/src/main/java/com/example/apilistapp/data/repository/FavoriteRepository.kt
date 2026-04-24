package com.example.apilistapp.data.repository

import com.example.apilistapp.APIListApplication
import com.example.apilistapp.data.mapper.toDomain
import com.example.apilistapp.data.mapper.toEntity
import com.example.apilistapp.domain.ClanDomain

class FavoriteRepository {
    val daoInterface = APIListApplication.database.clanDao()
    suspend fun saveAsFavorite(clan: ClanDomain) = daoInterface.addCharacter(clan.toEntity())
    suspend fun deleteFavorite(character: ClanDomain)= daoInterface.deleteCharacter(character.toEntity())

    suspend fun deleteAllFavorites() = daoInterface.deleteAllClans()

    suspend fun searchByName(tag: String): List<ClanDomain> = daoInterface.searchByName(tag)?.map { it.toDomain() } ?: emptyList()

    suspend fun isFavorite(tag: String) = daoInterface.getCharacterByTag(tag) != null

    suspend fun getFavorites(): MutableList<ClanDomain> {
        val favoritesList = mutableListOf<ClanDomain>()
        daoInterface.getAllCharacters().forEach {
            favoritesList.add(it.toDomain())
        }
        return favoritesList
    }
}
