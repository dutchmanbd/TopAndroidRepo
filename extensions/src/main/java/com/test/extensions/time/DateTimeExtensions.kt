package com.test.extensions.time

import java.text.SimpleDateFormat
import java.util.*

private const val UTC_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'"
private const val DATE_TIME_FORMAT = "MM-dd-yy HH:ss"

fun String.timeInMillis(): Date {
    val utcFormat = SimpleDateFormat(UTC_TIME_FORMAT, Locale.US)
    utcFormat.timeZone = TimeZone.getTimeZone("UTC")
    return try {
        utcFormat.parse(this) ?: Date()
    } catch (e: Exception) {
        Date()
    }
}

fun Long.formattedDateTime(): String {
    return SimpleDateFormat(DATE_TIME_FORMAT, Locale.getDefault()).format(
        this
    )
}

fun Date.formattedDateTime(): String {
    return SimpleDateFormat(DATE_TIME_FORMAT, Locale.getDefault()).format(
        this.time
    )
}