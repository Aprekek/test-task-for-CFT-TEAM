package com.example.cfttesttask.presentation.bindingadapters

import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout

@BindingAdapter("isError", "errorMessage")
fun TextInputLayout.isSetError(isError: Boolean, errorMessage: String) {
    error = if (isError) {
        isErrorEnabled = true
        errorMessage
    } else {
        isErrorEnabled = false
        null
    }
}