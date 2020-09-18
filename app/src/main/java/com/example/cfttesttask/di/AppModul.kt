package com.example.cfttesttask.di

import com.example.cfttesttask.presentation.fragments.MainViewModel
import com.example.cfttesttask.presentation.fragments.RegistrationViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { RegistrationViewModel() }
    viewModel { MainViewModel() }
}
