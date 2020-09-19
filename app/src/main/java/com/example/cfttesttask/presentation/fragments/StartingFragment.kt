package com.example.cfttesttask.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.cfttesttask.R
import com.example.cfttesttask.databinding.StartingFragmentBinding

class StartingFragment : Fragment() {

    private lateinit var binding: StartingFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initBinding(inflater, container)
        initListeners()

        return binding.root
    }

    private fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.starting_fragment, container, false
        )
    }

    private fun initListeners() {
        binding.logInButton.setOnClickListener {
        }
        binding.signUpButton.setOnClickListener {
            findNavController().navigate(
                StartingFragmentDirections.actionStartingFragmentToRegistrationFragment()
            )
        }
    }
}