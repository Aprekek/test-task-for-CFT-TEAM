package com.example.cfttesttask.utils

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