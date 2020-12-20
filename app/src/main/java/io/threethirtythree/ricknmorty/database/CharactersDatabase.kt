package io.threethirtythree.ricknmorty.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/** Abstract database definition that provides a singleton reference. */
@Database(
    entities = [Character::class],
    version = 1, exportSchema = false
)
abstract class CharactersDatabase : RoomDatabase() {

    abstract fun charDao(): CharactersDao

    companion object {
        @Volatile
        private var INSTANCE: CharactersDatabase? = null

        fun getDatabase(context: Context): CharactersDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CharactersDatabase::class.java,
                    "char_database"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                instance
            }
        }
    }

}