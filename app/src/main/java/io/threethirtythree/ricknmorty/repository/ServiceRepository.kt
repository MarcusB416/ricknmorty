package io.threethirtythree.ricknmorty.repository

import io.threethirtythree.ricknmorty.service.RickMortyService
/** Repository used to get a response from the RickMortyService. */
class ServiceRepository(
    private val service: RickMortyService
) {

    /** Async function to fetch characters from the service. */
    suspend fun loadData() = service.getCharactersResponse()
}