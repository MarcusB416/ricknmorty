package io.threethirtythree.ricknmorty.app

import android.app.Application
import io.threethirtythree.ricknmorty.database.CharactersDatabase
import io.threethirtythree.ricknmorty.repository.CharactersRepository
import io.threethirtythree.ricknmorty.repository.ServiceRepository
import io.threethirtythree.ricknmorty.service.RickMortyServiceFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

/** Stores a couple lazy ass singletons that shouldn't have more than one instance. */
class CustomApp : Application() {
    val applicationScope by lazy { CoroutineScope(SupervisorJob()) }
    val database by lazy { CharactersDatabase.getDatabase(this) }
    val characterRepository by lazy { CharactersRepository(database.charDao()) }
    val serviceRepository by lazy { ServiceRepository(RickMortyServiceFactory.getService()) }
}