package io.threethirtythree.effectivekotlin.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import io.threethirtythree.effectivekotlin.model.Word
import io.threethirtythree.effectivekotlin.repository.dao.WordDao

class WordRepository(private val wordDao: WordDao) {

    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    val allWords: LiveData<List<Word>> = wordDao.getAlphabetizedWords()

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(word: Word) {
        wordDao.insert(word)
    }
}