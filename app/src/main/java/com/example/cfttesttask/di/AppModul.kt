package com.example.cfttesttask.di

import androidx.room.Room
import com.example.cfttesttask.data.PersonDatabase
import com.example.cfttesttask.presentation.fragments.MainViewModel
import com.example.cfttesttask.presentation.fragments.RegistrationViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

private val viewModelModule = module {
    viewModel { RegistrationViewModel() }
    viewModel { MainViewModel() }
}

private val roomDatabaseModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            PersonDatabase::class.java,
            PersonDatabase.DATABASE_NAME
        ).fallbackToDestructiveMigration().build()
    }
    factory { get<PersonDatabase>().personDao() }
}

val appModules = listOf(viewModelModule, roomDatabaseModule)