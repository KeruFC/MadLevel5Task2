package com.example.madlevel5task2.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.madlevel5task2.R
import com.example.madlevel5task2.model.Game
import com.example.madlevel5task2.model.GamesViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_games.*
import kotlinx.android.synthetic.main.item_game.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class GamesFragment : Fragment() {

    private val viewModel: GamesViewModel by viewModels()
    private var games: ArrayList<Game> = arrayListOf()
    private lateinit var gameAdapter: GameAdapter

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_games, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeAddGameResult()
        initRv()
    }

    private fun initRv(){
        gameAdapter = GameAdapter(games)
        rvGames.adapter = gameAdapter
        rvGames.layoutManager = LinearLayoutManager(activity)
    }

    private fun observeAddGameResult() {
        viewModel.games.observe(viewLifecycleOwner, Observer { gamesAvailable ->
            this@GamesFragment.games.clear()
            this@GamesFragment.games.addAll(gamesAvailable)
        })
    }
}