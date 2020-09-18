package com.example.cfttesttask.presentation.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.cfttesttask.R
import com.example.cfttesttask.domain.constants.PreferencesKeys
import com.example.cfttesttask.domain.constants.StatusConstants
import com.example.cfttesttask.presentation.fragments.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setStartingFragment()
    }

    private fun setStartingFragment() {
        val status = getPreferences(MODE_PRIVATE).getInt(PreferencesKeys.STATUS, -1)
        if (status == StatusConstants.SAVED) {
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.host_fragment, MainFragment())
            fragmentTransaction.commit()
        }
    }
}