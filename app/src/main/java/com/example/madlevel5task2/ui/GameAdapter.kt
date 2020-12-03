package com.example.madlevel5task2.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.madlevel5task2.R
import com.example.madlevel5task2.model.Game
import kotlinx.android.synthetic.main.item_game.view.*
import java.util.*

class GameAdapter(private val games: List<Game>) :
    RecyclerView.Adapter<GameAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun databind(game: Game) {

            val date: Calendar = Calendar.getInstance()
            date.time = game.date
            val day = date.get(Calendar.DAY_OF_WEEK)
            val month = date.get(Calendar.MONTH)
            val year = date.get(Calendar.YEAR)

            itemView.tvGame.text = game.title
            itemView.tvPlatforms.text = game.platform
            itemView.tvReleaseDate.text =
                String.format(
                    "Release: %s %s %s",
                    day,
                    month,
                    year
                )
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_game, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.databind(games[position])
    }

    override fun getItemCount(): Int {
        return games.size
    }
}