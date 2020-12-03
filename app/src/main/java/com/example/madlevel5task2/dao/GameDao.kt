package com.example.madlevel5task2.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.madlevel5task2.model.Game

@Dao
interface GameDao {

    @Insert
    suspend fun insertGame(game: Game)

    @Query("SELECT * FROM gameTable LIMIT 1")
    fun getAllGames(): LiveData<List<Game>>

    @Query("DELETE FROM gameTable")
    suspend fun deleteGames()
}