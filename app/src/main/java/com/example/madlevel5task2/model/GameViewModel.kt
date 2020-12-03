package com.example.madlevel5task2.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.madlevel5task2.repository.GameRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.Month
import java.util.*

class GamesViewModel(application: Application) : AndroidViewModel(application) {
    private val mainScope = CoroutineScope(Dispatchers.Main)
    private val gameRepository = GameRepository(application.applicationContext)

    val games: LiveData<List<Game>> = gameRepository.getAllGames()
    val error = MutableLiveData<String>()
    val success = MutableLiveData<Boolean>()

    fun insertGameBackLog(
        title: String,
        platform: String,
        date: Date
    ) {
        val gameBacklog = Game(
            title = title,
            platform = platform,
            date = date
        )

        if (isValidGameBacklog(gameBacklog)) {
            mainScope.launch {
                withContext(Dispatchers.IO) {
                    gameRepository.insertGame(gameBacklog)
                }
                success.value = true
            }
        }
    }

    fun deleteGames() {
        mainScope.launch {
            withContext(Dispatchers.IO) {
                gameRepository.deleteGames()
            }
            success.value = true
        }
    }

    private fun isValidGameBacklog(game: Game): Boolean {
        return when {
            game.title.isBlank() -> {
                error.value = "Please fill in a valid title."
                false
            }
            game.platform.isBlank() -> {
                error.value = "Please fill in a valid platform."
                false
            }
//            game.date.isBlank() || game.month.isBlank() || game.year.isBlank() -> {
//                error.value = "Please fill in a valid date."
//                false
//            }

            else -> true
        }
    }
}