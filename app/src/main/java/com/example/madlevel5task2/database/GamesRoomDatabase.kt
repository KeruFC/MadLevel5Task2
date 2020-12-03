package com.example.madlevel5task2.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.madlevel5task2.dao.GameDao
import com.example.madlevel5task2.model.Game


@Database(entities = [Game::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class GamesRoomDatabase : RoomDatabase() {

    abstract fun gameDao(): GameDao

    companion object {
        private val DATABASE_NAME = "GAME_DATABASE"

        @Volatile
        private var DATABASE_INSTANCE: GamesRoomDatabase? = null

        fun getDatabase(context: Context): GamesRoomDatabase? {
            if (DATABASE_INSTANCE == null) {
                synchronized(GamesRoomDatabase::class.java) {
                    if (DATABASE_INSTANCE == null) {
                        DATABASE_INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            GamesRoomDatabase::class.java, DATABASE_NAME
                        ).build()
                    }
                }
            }
            return DATABASE_INSTANCE
        }
    }
}