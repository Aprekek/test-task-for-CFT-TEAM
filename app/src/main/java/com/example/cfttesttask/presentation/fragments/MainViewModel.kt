package com.example.cfttesttask.presentation.fragments

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cfttesttask.data.PersonDao
import com.example.cfttesttask.data.PersonEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.java.KoinJavaComponent.get

class MainViewModel : ViewModel() {

    private val personDao = get(PersonDao::class.java)
    val personEntity = MutableLiveData<PersonEntity>(PersonEntity())

    fun loadPerson(id: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            val person = personDao.getPerson(id)
            withContext(Dispatchers.Main) {
                personEntity.value = person
            }
        }
    }
}