package com.example.cfttesttask.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.cfttesttask.R
import com.example.cfttesttask.databinding.LoginFragmentBinding
import com.example.cfttesttask.domain.constants.PreferencesKeys
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment() {

    private lateinit var binding: LoginFragmentBinding
    private val viewModel: LoginViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initBinding(inflater, container)
        initListeners()
        initObservers()

        return binding.root
    }

    private fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.login_fragment, container, false
        )
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
    }

    private fun initListeners() {
        binding.loginButton.setOnClickListener {
            viewModel.isPersonWithEnteredDataExist()
        }
    }

    private fun initObservers() {
        viewModel.personId.observe(viewLifecycleOwner) {
            it?.let {
                savePersonIdToPreferences(it)
                navigateToMainFragment()
            }
        }

        viewModel.password.observe(viewLifecycleOwner) {
            binding.passwordTil.error = null
        }
    }

    private fun savePersonIdToPreferences(id: Long) {
        this.activity?.getPreferences(Context.MODE_PRIVATE)?.let {
            it.edit()?.let { _edit ->
                _edit.putLong(PreferencesKeys.ID, id)
                _edit.commit()
            }
        }
    }

    private fun navigateToMainFragment() {
        findNavController().navigate(
            LoginFragmentDirections.actionLoginFragmentToMainFragment()
        )
    }
}