package com.example.cfttesttask.domain.extentions

import androidx.lifecycle.MediatorLiveData

fun MediatorLiveData<Int>.setOrRemoveBitFlag(state: Boolean, flag: Int) {
    if (state) {
        if (value?.and(flag) != flag)
            value = value?.or(flag)
    } else
        value = value?.and(flag.inv())
}
