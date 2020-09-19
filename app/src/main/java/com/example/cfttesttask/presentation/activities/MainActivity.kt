package com.example.cfttesttask.presentation.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.example.cfttesttask.R
import com.example.cfttesttask.domain.constants.PreferencesKeys
import com.example.cfttesttask.domain.constants.StatusConstants

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setStartingFragment()
    }

    private fun setStartingFragment() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        val graph = navController.navInflater.inflate(R.navigation.nav_graph)

        val status = getPreferences(MODE_PRIVATE).getInt(PreferencesKeys.STATUS, -1)
        graph.startDestination = if (status == StatusConstants.SAVED) {
            R.id.mainFragment
        } else {
            R.id.startingFragment
        }
        navController.graph = graph
    }
}