package com.who.climasense.utils

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

fun epochToDate(epochTime: Int): String {
    val sdf = SimpleDateFormat("MMM d", Locale.getDefault()) // Month and day
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = epochTime.toLong() * 1000 // Converting seconds to milliseconds
    return sdf.format(calendar.time)
}

fun epochToTime(epochTime: Long): String {
    val sdf = SimpleDateFormat("HH:mm", Locale.getDefault())
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = epochTime * 1000 // Converting seconds to milliseconds
    return sdf.format(calendar.time)
}