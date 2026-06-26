package com.utsav.nexusnotes.core.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.concurrent.TimeUnit

fun Long.toRelativeTime(): String {

    val now = System.currentTimeMillis()

    val diff = now - this

    return when {

        diff < TimeUnit.MINUTES.toMillis(1) ->
            "Just now"

        diff < TimeUnit.HOURS.toMillis(1) ->
            "${TimeUnit.MILLISECONDS.toMinutes(diff)} min ago"

        diff < TimeUnit.DAYS.toMillis(1) ->
            "${TimeUnit.MILLISECONDS.toHours(diff)} hr ago"

        diff < TimeUnit.DAYS.toMillis(2) ->
            "Yesterday"

        diff < TimeUnit.DAYS.toMillis(7) ->
            "${TimeUnit.MILLISECONDS.toDays(diff)} days ago"

        else ->

            SimpleDateFormat(
                "dd MMM",
                Locale.getDefault()
            ).format(Date(this))

    }

}