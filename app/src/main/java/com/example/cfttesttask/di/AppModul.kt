package com.example.cfttesttask.di

import com.example.cfttesttask.fragments.RegistrationViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { RegistrationViewModel() }
}
