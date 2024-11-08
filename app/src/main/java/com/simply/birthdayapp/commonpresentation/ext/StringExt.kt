package com.simply.birthdayapp.commonpresentation.ext

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

fun String.isToday(): Boolean {
    val formatter = SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH)
    val date = formatter.parse(this)
    val today = Calendar.getInstance()

    return date?.let {
        val birthdayCalendar = Calendar.getInstance().apply { time = date }
        birthdayCalendar.get(Calendar.DAY_OF_MONTH) == today.get(Calendar.DAY_OF_MONTH) && birthdayCalendar.get(
            Calendar.MONTH
        ) == today.get(Calendar.MONTH)
    } ?: false
}
