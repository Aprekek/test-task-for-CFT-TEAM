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
        val status = getPreferences(MODE_PRIVATE).getLong(
            PreferencesKeys.ID, StatusConstants.ID_NOT_SET
        )
        if (status != StatusConstants.ID_NOT_SET) {
            val navHostFragment =
                supportFragmentManager.findFragmentById(R.id.host_fragment) as NavHostFragment
            val navController = navHostFragment.navController
            val graph = navController.navInflater.inflate(R.navigation.nav_graph)
            graph.startDestination = R.id.mainFragment
            navController.graph = graph
        }
    }
}