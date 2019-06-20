package net.darksky.example.util

import java.text.SimpleDateFormat
import java.util.*

object DateUtils {
    @JvmStatic
    fun format(date: Date): String {
        val format = SimpleDateFormat("dd MMM\nhh a", Locale.getDefault())
        return format.format(date)
    }

    @JvmStatic
    fun formatCurrently(unixtimestamp: Long): String {
        val format = SimpleDateFormat("dd MMM\nh a", Locale.getDefault())
        return format.format(Date(unixtimestamp * 1000))
    }

    @JvmStatic
    fun formatHourly(unixtimestamp: Long): String {
        val format = SimpleDateFormat("h a\ndd MMM", Locale.getDefault())
        return format.format(Date(unixtimestamp * 1000))
    }
}