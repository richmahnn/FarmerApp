package com.lubulwa.testapp.utils

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.text.SimpleDateFormat
import java.util.*

inline fun <reified VM: ViewModel> AppCompatActivity.viewModelOf(factory: ViewModelProvider.Factory) =
    ViewModelProvider(this, factory).get(VM::class.java)

fun getTimeFromTimestamp(timestamp: Long): String {
    val simpleDate = SimpleDateFormat("h:mm a", Locale.getDefault())
    return simpleDate.format(timestamp)
}
