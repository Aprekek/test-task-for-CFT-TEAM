package com.example.cfttesttask.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.cfttesttask.utils.isContainsNumber
import com.example.cfttesttask.utils.isContainsUppercase

class RegistrationViewModel : ViewModel() {

    private companion object {

        const val BIRTH_DATE_STR = "Дата рождения"
        const val DEFAULT_YEAR = 2001
        const val MIN_LENGTH = 8
        const val MAX_LENGTH = 16

        private val monthsString = arrayOf(
            "января", "февраля", "марта", "апреля", "мая", "июня",
            "июля", "августа", "сентября", "октября", "ноября", "декабря"
        )
    }

    val name = MutableLiveData<String>()
    val secondName = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val confirmedPassword = MutableLiveData<String>()

    val isPasswordNotValid: LiveData<Boolean> = Transformations.map(password) {
        !(it.isContainsNumber() && it.isContainsUppercase() &&
                (it.length >= MIN_LENGTH) && (it.length <= MAX_LENGTH))
    }
    val isConfPasswordNotValid: LiveData<Boolean> = Transformations.map(confirmedPassword) {
        !(it.equals(password.value, false))
    }

    val birthDate = MutableLiveData<String>(BIRTH_DATE_STR)
    private var _year: Int = DEFAULT_YEAR
    val year: Int
        get() = _year
    private var _month: Int = 0
    val month: Int
        get() = _month
    private var _day: Int = 0
    val day: Int
        get() = _day

    fun onDateSelected(year: Int, month: Int, day: Int) {
        _year = year
        _month = month
        _day = day
        birthDate.value = "$day ${monthsString[month]} $year"
    }
}