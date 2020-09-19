package com.example.cfttesttask.presentation.bindingadapters

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.cfttesttask.R

@BindingAdapter("name", "secondName")
fun TextView.setFullName(name: String, secondName: String) {
    text = resources.getString(R.string.full_name, name, secondName)
}