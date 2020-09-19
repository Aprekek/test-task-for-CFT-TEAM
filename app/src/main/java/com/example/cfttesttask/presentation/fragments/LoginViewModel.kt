package com.example.cfttesttask.presentation.fragments

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cfttesttask.data.PersonDao
import com.example.cfttesttask.domain.extentions.setOrRemoveBitFlag
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.java.KoinJavaComponent.get

class LoginViewModel : ViewModel() {

    companion object {

        const val ALL_VALID_FL = 3
        const val NICKNAME_VALID_FL = 1
        const val PASSWORD_VALID_FL = 2
    }

    private val personDao = get(PersonDao::class.java)
    val personId = MutableLiveData<Long?>()

    val nickname = MutableLiveData<String>()
    val password = MutableLiveData<String>()

    val currentFieldsValidationFlag = MediatorLiveData<Int>().apply {
        value = 0
        addSource(nickname) {
            this.setOrRemoveBitFlag(state = !it.isBlank(), flag = NICKNAME_VALID_FL)
        }
        addSource(password) {
            this.setOrRemoveBitFlag(state = !it.isBlank(), flag = PASSWORD_VALID_FL)
        }
    }

    val isPersonNotExist = MutableLiveData<Boolean>()

    fun isPersonWithEnteredDataExist() {
        viewModelScope.launch(Dispatchers.IO) {
            val id = personDao.getPersonId(nickname.value!!, password.value!!)

            withContext(Dispatchers.Main) {
                personId.value = id
                isPersonNotExist.value = id == null
            }
        }
    }
}