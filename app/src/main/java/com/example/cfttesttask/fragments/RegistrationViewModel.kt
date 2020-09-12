package com.example.cfttesttask.fragments

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RegistrationViewModel : ViewModel() {

    companion object {
        const val GET_BIRTH_DATE = "Дата рождения"
        const val DEFAULT_YEAR = 2001
    }

    private val monthsString = arrayOf(
        "января", "февраля", "марта", "апреля", "мая", "июня",
        "июля", "августа", "сентября", "октября", "ноября", "декабря"
    )

    val name = MutableLiveData<String>()
    val secondName = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val confirmedPassword = MutableLiveData<String>()

    val birthDate = MutableLiveData<String>(GET_BIRTH_DATE)
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