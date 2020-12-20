package io.threethirtythree.ricknmorty.repository

import io.threethirtythree.ricknmorty.database.Character
import io.threethirtythree.ricknmorty.database.CharactersDao
import kotlinx.coroutines.flow.Flow

/** Repository that makes queries to the CharactersDao. */
class CharactersRepository(private val charactersDao: CharactersDao){

    /** Our single source of truth for what is in the database */
    val allCharacters: Flow<List<Character>> = charactersDao.getCharacters()

//    fun getCharacters() = charactersDao.getCharacters()

//    fun getCharacterById(id: Long) = charactersDao.getCharacterById(id)

    suspend fun insert(character: Character) = charactersDao.insert(character)

    suspend fun insertAll(characters: List<Character>) = charactersDao.insertAll(characters)

    suspend fun deleteAll() = charactersDao.deleteAll()

}

