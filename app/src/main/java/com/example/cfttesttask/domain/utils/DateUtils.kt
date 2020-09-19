package com.example.cfttesttask.domain.utils

import java.util.*

fun isDateValid(day: Int, month: Int, year: Int): Boolean {
    val calendar = Calendar.getInstance()
    val curYear = calendar.get(Calendar.YEAR)

    if (curYear < year)
        return false
    else if (curYear == year) {
        val curMonth = calendar.get(Calendar.MONTH)
        if (curMonth < month)
            return false
        else if (curMonth == month) {
            if (calendar.get(Calendar.DAY_OF_MONTH) < day)
                return false
        }
    }
    return true
}