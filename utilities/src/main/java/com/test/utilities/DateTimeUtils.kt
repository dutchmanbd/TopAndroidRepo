package com.test.utilities

import java.text.SimpleDateFormat
import java.util.*

object DateTimeUtils {

    private const val UTC_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'"
    private const val DATE_TIME_FORMAT = "MM-dd-yy HH:ss"

    fun getLocalDate(serverDate: String): String {
        val utcFormat = SimpleDateFormat(UTC_TIME_FORMAT, Locale.US)
        utcFormat.timeZone = TimeZone.getTimeZone("UTC")
        return try {
            SimpleDateFormat(DATE_TIME_FORMAT, Locale.getDefault()).format(
                utcFormat.parse(serverDate)?.time
            )
        } catch (e: Exception) {
            try {
                val replaceDate = serverDate.replace("T", " ").replace("Z", "")
                val (date, time) = replaceDate.trim().split(" ")
                val (year, month, day) = date.split("-")
                val (hour, _, second) = time.split(":")
                val sb = StringBuilder()
                sb.append(month).append("-")
                    .append(day).append("-")
                    .append(year).append(" ")
                    .append(hour).append(":").append(second)
                sb.toString()
            } catch (e: Exception) {
                ""
            }
        }
    }

}