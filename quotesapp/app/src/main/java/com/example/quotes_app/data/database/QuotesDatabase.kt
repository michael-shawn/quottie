package com.example.quotes_app.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.quotes_app.data.database.dao.QuotesDao
import com.example.quotes_app.data.database.model.Quotes


@Database(
    entities = [Quotes::class],
    version = 1
)
abstract class QuotesDatabase : RoomDatabase() {

    abstract fun quotesDao(): QuotesDao

    companion object {

        @Volatile
        private var instance: QuotesDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance
            ?: synchronized(LOCK) {
                instance ?: buildDatabase(context).also {
                        instance = it
                }
            }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            QuotesDatabase::class.java,
            "Quotes.db"
        ).build()
    }
}