package com.example.cfttesttask.domain.extentions

fun String.isContainsNumber(): Boolean {
    forEach {
        if (it.isDigit()) return true
    }
    return false
}

fun String.isContainsUppercase(): Boolean {
    forEach {
        if (it.isUpperCase()) return true
    }
    return false
}

fun String.isContainsLowercase(): Boolean {
    forEach {
        if (it.isLowerCase()) return true
    }
    return false
}

fun String.removeExtraSpaces(): String {
    var newString = ""
    forEach {
        if (it != ' ')
            newString += it
    }
    return newString
}