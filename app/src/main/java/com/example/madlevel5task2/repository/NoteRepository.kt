package com.example.madlevel5task2.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.madlevel5task2.dao.GameDao
import com.example.madlevel5task2.database.GamesRoomDatabase
import com.example.madlevel5task2.model.Game


class GameBacklogRepository(context: Context) {
    private val gameDao: GameDao

    init {
        val database = GamesRoomDatabase.getDatabase(context)
        gameDao = database!!.gameDao()
    }

    fun getAllGames(): LiveData<List<Game>> {
        return gameDao.getAllGames()
    }

    suspend fun insertGame(game: Game) {
        gameDao.insertGame(game)
    }

    suspend fun deleteGames() {
        gameDao.deleteGames()
    }
}