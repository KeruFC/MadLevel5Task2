package com.example.madlevel5task2.ui

import android.os.Build
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.madlevel5task2.R
import com.example.madlevel5task2.model.GamesViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_add_game.*
import java.lang.NullPointerException
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private val viewModel: GamesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        navController = findNavController(R.id.nav_host_fragment)
//        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                    .setAction("Action", null).show()
//        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.FirstFragment -> {
                    fab.setImageResource(android.R.drawable.ic_menu_edit)
                    fab.setOnClickListener {
                        supportActionBar?.setDisplayHomeAsUpEnabled(true)
                        supportActionBar?.setDisplayShowHomeEnabled(true)
                        navController.navigate(R.id.action_FirstFragment_to_SecondFragment)
                    }
                    supportActionBar?.setDisplayHomeAsUpEnabled(false)
                    supportActionBar?.setDisplayShowHomeEnabled(false)
                }
                R.id.SecondFragment -> {
                    val date = Calendar.getInstance()

                    fab.setImageResource(android.R.drawable.ic_menu_save)
                    fab.setOnClickListener {
                        var day = editTextDay.text.toString()
                        if(day.isBlank()){
                            day = "0"
                        }
                        date.set(Calendar.DAY_OF_MONTH, Integer.parseInt(day))
                        var month = editTextMonth.text.toString()
                        if(month.isBlank()){
                            month = "0"
                        }
                        date.set(Calendar.MONTH, Integer.parseInt(month))
                        var year = editTextYear.text.toString()
                        if(year.isBlank()){
                            year = "0"
                        }
                        date.set(Calendar.YEAR, Integer.parseInt(year))

                        viewModel.insertGameBackLog(
                            tilGameTitle.editText.toString(),
                            tilGamePlatform.editText.toString(),
                            Date.from(date.toInstant())
                        )

                        navController.navigate(R.id.action_SecondFragment_to_FirstFragment)
                    }
                }
            }
        }
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}