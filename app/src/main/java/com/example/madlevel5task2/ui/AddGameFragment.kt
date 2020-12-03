package com.example.madlevel5task2.ui

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.madlevel5task2.R
import com.example.madlevel5task2.model.Game
import com.example.madlevel5task2.model.GameViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_add_game.*
import java.time.DateTimeException
import java.time.LocalDate
import java.util.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class AddGameFragment : Fragment() {

    private val viewModel: GameViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_game, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<FloatingActionButton>(R.id.fab2).setOnClickListener {
            onSaveGame()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun onSaveGame() {
        val title = editGameTitle.text.toString()
        val date: LocalDate

        try {
            date = LocalDate.of(
                editTextYear.text.toString().toIntOrNull()!!,
                editTextMonth.text.toString().toIntOrNull()!!,
                editTextDay.text.toString().toIntOrNull()!!
            )

        } catch (e: NullPointerException) {
            Toast.makeText(
                activity,
                R.string.invalid_date,
                Toast.LENGTH_LONG
            ).show()
            return
        } catch (e: DateTimeException) {
            Toast.makeText(
                activity,
                R.string.invalid_date,
                Toast.LENGTH_LONG
            ).show()
            return
        }

        val platform = editGamePlatform.text.toString()

        if (title.isNotBlank() && platform.isNotBlank()) {
            viewModel.insertGame(Game(title, platform, date))

            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        } else {
            Toast.makeText(
                activity,
                R.string.empty_field,
                Toast.LENGTH_LONG
            ).show()
        }
    }
}