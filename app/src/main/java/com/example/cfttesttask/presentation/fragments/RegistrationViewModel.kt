package com.example.cfttesttask.presentation.fragments

import androidx.lifecycle.*
import com.example.cfttesttask.data.PersonDao
import com.example.cfttesttask.data.PersonEntity
import com.example.cfttesttask.domain.extentions.*
import com.example.cfttesttask.domain.utils.isDateValid
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.java.KoinJavaComponent.get

class RegistrationViewModel : ViewModel() {

    companion object {

        private const val BIRTH_DATE_STR = "Дата рождения"
        private const val DEFAULT_YEAR = 2001
        private const val MIN_LENGTH = 8
        private const val MAX_LENGTH = 16

        private val monthsString = arrayOf(
            "января", "февраля", "марта", "апреля", "мая", "июня",
            "июля", "августа", "сентября", "октября", "ноября", "декабря"
        )

        const val ALL_VALID_FL = 63
        private const val NAME_VALID_FL = 1
        private const val SECOND_NAME_VALID_FL = 2
        private const val PASSWORD_VALID_FL = 4
        private const val CONF_PASSWORD_VALID_FL = 8
        private const val DATE_IS_SET_FL = 16
        private const val NICKNAME_FL = 32
    }

    private val personDao = get(PersonDao::class.java)
    val personId = MutableLiveData<Long>()

    val name = MutableLiveData<String>()
    val secondName = MutableLiveData<String>()
    val nickname = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val confirmedPassword = MutableLiveData<String>()

    val isSetErrorToNickname = MutableLiveData<Boolean>()
    val isPasswordNotValid: LiveData<Boolean> = Transformations.map(password) {
        !(it.isContainsNumber() && it.isContainsUppercase() && it.isContainsLowercase() &&
                (it.length >= MIN_LENGTH) && (it.length <= MAX_LENGTH))
    }
    val isConfPasswordNotValid: LiveData<Boolean> = Transformations.map(confirmedPassword) {
        !(it.equals(password.value, false))
    }

    val birthDate = MutableLiveData(BIRTH_DATE_STR)
    private var isBirthDateSet = MutableLiveData(false)
    private var _year: Int = DEFAULT_YEAR
    val year: Int
        get() = _year
    private var _month: Int = 0
    val month: Int
        get() = _month
    private var _day: Int = 0
    val day: Int
        get() = _day

    val currentFieldsValidationFlag: LiveData<Int> = MediatorLiveData<Int>().apply {
        value = 0
        addSource(name) {
            this.setOrRemoveBitFlag(state = !it.isBlank(), flag = NAME_VALID_FL)
        }
        addSource(secondName) {
            this.setOrRemoveBitFlag(state = !it.isBlank(), flag = SECOND_NAME_VALID_FL)
        }
        addSource(nickname) {
            this.setOrRemoveBitFlag(state = !it.isBlank(), flag = NICKNAME_FL)
        }
        addSource(isPasswordNotValid) {
            this.setOrRemoveBitFlag(state = !it, flag = PASSWORD_VALID_FL)
        }
        addSource(isConfPasswordNotValid) {
            this.setOrRemoveBitFlag(state = !it, flag = CONF_PASSWORD_VALID_FL)
        }
        addSource(isBirthDateSet) {
            this.setOrRemoveBitFlag(state = it, flag = DATE_IS_SET_FL)
        }
    }

    fun onDateSelected(year: Int, month: Int, day: Int): Boolean {
        return if (isDateValid(day, month, year)) {
            _year = year
            _month = month
            _day = day
            birthDate.value = "$day ${monthsString[month]} $year"
            isBirthDateSet.value = true
            true
        } else
            false
    }

    fun isNicknameExist() {
        viewModelScope.launch(Dispatchers.IO) {
            nickname.value?.let { _nickname ->
                val isExist = personDao.getPersonId(_nickname) != null

                withContext(Dispatchers.Main) {
                    isSetErrorToNickname.value = isExist
                }
            }
        }
    }

    fun addPersonRegInfoToDB() {
        viewModelScope.launch(Dispatchers.IO) {
            val id = personDao.addPerson(
                PersonEntity(
                    nickName = nickname.value!!.removeExtraSpaces(),
                    password = password.value!!,
                    name = name.value!!.removeExtraSpaces(),
                    secondName = secondName.value!!.removeExtraSpaces(),
                    birthDate = birthDate.value!!
                )
            )

            withContext(Dispatchers.Main) {
                personId.value = id
            }
        }
    }
}