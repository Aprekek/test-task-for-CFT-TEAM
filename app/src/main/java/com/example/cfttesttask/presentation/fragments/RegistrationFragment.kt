package com.example.cfttesttask.presentation.fragments

import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.cfttesttask.R
import com.example.cfttesttask.databinding.RegistrationFragmentBinding
import com.example.cfttesttask.domain.constants.PreferencesKeys
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegistrationFragment : Fragment() {

    private lateinit var binding: RegistrationFragmentBinding
    private val viewModel: RegistrationViewModel by viewModel()

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
            inflater, R.layout.registration_fragment, container, false
        )
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
    }

    private fun initListeners() {
        binding.dateButton.setOnClickListener {
            initDatePicker()
        }

        binding.registrationButton.setOnClickListener {
            viewModel.isNicknameExist()
        }
    }

    private fun initObservers() {
        viewModel.nicknameExist.observe(viewLifecycleOwner) { isExist ->
            if (!isExist) {
                viewModel.addPersonRegInfoToDB()
            }
        }

        viewModel.personId.observe(viewLifecycleOwner) {
            savePersonIdToPreferences(it)
            navigateToMainFragment()
        }
    }

    private fun initDatePicker() {
        val listener = DatePickerDialog.OnDateSetListener { _, year, month, day ->
            binding.viewModel?.onDateSelected(year, month, day)
        }
        DatePickerDialog(
            requireContext(),
            listener,
            viewModel.year,
            viewModel.month,
            viewModel.day
        ).show()
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
            RegistrationFragmentDirections.actionRegistrationFragmentToMainFragment(
                viewModel.name.value
                    ?: throw NullPointerException("viewModel.name.value must be not null")
            )
        )
    }
}