package com.example.cryptostock3.presentation

import java.sql.Date
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone

fun convertTime(time: Long?): String {
    if (time == null) return ""
    val stamp = Timestamp(time * 1000)
    val date = Date(stamp.time)
    val pattern = "HH:mm:ss"
    val sdf = SimpleDateFormat(pattern, Locale.getDefault())
    sdf.timeZone = TimeZone.getDefault()
    return sdf.format(date)
}