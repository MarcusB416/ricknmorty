package io.threethirtythree.ricknmorty.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

/** Definition of how we will be interacting with our database. */
@Dao
interface CharactersDao {

    @Query("SELECT * FROM char_table")
    fun getCharacters(): Flow<List<Character>>

    @Query("SELECT * FROM char_table WHERE id = :id")
    fun getCharacterById(id: Long): Flow<Character>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll( characters: List<Character>)

    @Query("DELETE FROM char_table")
    suspend fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(character: Character)

}