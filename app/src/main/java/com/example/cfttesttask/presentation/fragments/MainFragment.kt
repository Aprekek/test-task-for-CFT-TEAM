package com.example.cfttesttask.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.cfttesttask.R
import com.example.cfttesttask.databinding.MainFragmentBinding
import com.example.cfttesttask.domain.constants.PreferencesKeys
import com.example.cfttesttask.domain.constants.StatusConstants
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : Fragment() {

    private lateinit var binding: MainFragmentBinding
    private val viewModel: MainViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initBinding(inflater, container)
        initListeners()

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        saveStatusInPreferences()
    }

    private fun saveStatusInPreferences() {
        this.activity?.getPreferences(Context.MODE_PRIVATE)?.let { _pref ->
            _pref.edit()?.let { _edit ->
                with(_edit) {
                    putInt(PreferencesKeys.STATUS, StatusConstants.SAVED)
                    commit()
                }
            }
        }
    }

    private fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.main_fragment, container, false
        )
        binding.viewModel = viewModel
    }

    private fun initListeners() {
        binding.greetingButton.setOnClickListener {
            Toast.makeText(
                requireContext(),
                getString(R.string.greeting_message),
                Toast.LENGTH_LONG
            ).show()
        }
    }
}