package io.threethirtythree.ricknmorty.service

import io.threethirtythree.ricknmorty.database.Character
import io.threethirtythree.ricknmorty.database.CharacterResponse
import retrofit2.http.GET
import retrofit2.http.Path

/** Definition of routes we're interested in */
interface RickMortyService {

    /** Returns a list of Characters. */
    @GET("api/character")
    suspend fun getCharactersResponse() : CharacterResponse

    /** Returns a single Character. */
    @GET("api/character/{id}")
    suspend fun getCharacterById(@Path("id") id: Long) : Character
}