package io.threethirtythree.effectivekotlin

import android.app.Application
import io.threethirtythree.effectivekotlin.repository.WordRepository
import io.threethirtythree.effectivekotlin.repository.db.WordRoomDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class MVVMApplication : Application() {
    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    val applicationScope by lazy { CoroutineScope(SupervisorJob()) }
    val database by lazy { WordRoomDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { WordRepository(database.wordDao()) }
}