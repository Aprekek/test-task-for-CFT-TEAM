package com.example.cfttesttask.bindingadapters

import androidx.databinding.BindingAdapter
import com.example.cfttesttask.R
import com.google.android.material.button.MaterialButton

@BindingAdapter("isButtonActive")
fun MaterialButton.isButtonActive(isActive: Boolean) {
    isClickable = if (isActive) {
        setBackgroundColor(resources.getColor(R.color.colorPrimary))
        true
    } else {
        setBackgroundColor(resources.getColor(R.color.colorAccent))
        false
    }
}