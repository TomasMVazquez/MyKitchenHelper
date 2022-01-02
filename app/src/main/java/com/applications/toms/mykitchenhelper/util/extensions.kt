package com.applications.toms.mykitchenhelper.util

import java.util.*
import java.util.concurrent.TimeUnit

fun String.toTimerLongFormat(): Long {
    var text = this

    var textSplitted = text.split(":")

    val hours = TimeUnit.HOURS.toMillis(textSplitted[0].trim().toLong())
    val minutes = TimeUnit.MINUTES.toMillis(textSplitted[1].trim().toLong())
    val seconds = TimeUnit.SECONDS.toMillis(textSplitted[2].trim().toLong())

    return hours + minutes + seconds
}

fun String.toTimerLong(): Long {
    var text = this

    var textSplitted = text.split(":")

    var timeSec: Long = 0

    timeSec = (textSplitted[0].toLong() * 3600) + (textSplitted[1].toLong() * 60) + textSplitted[2].toLong()

    return timeSec
}

fun String.toTimeFormat(): String {
    var text = this
    while (text.length < 6){
        text = "0".plus(text)
    }

    val textChunked = text.chunked(2)

    return when(textChunked.size){
        1 -> "00 : 00 : ${textChunked[0]}"
        2 -> "00 : ${textChunked[0]} : ${textChunked[1]}"
        3 -> "${textChunked[0]} : ${textChunked[1]} : ${textChunked[2]}"
        else -> "00 : 00 : 00"
    }
}

fun String.toTimer(): Long {
    val textChunked = (if (this.length % 2 != 0) "0$this" else this).chunked(2)

    var timeSec: Long = 0

    when(textChunked.size){
        1 -> timeSec = textChunked[0].toLong()
        2 -> timeSec = (textChunked[0].toLong() * 60) + textChunked[1].toLong()
        3 -> timeSec = (textChunked[0].toLong() * 3600) + (textChunked[1].toLong() * 60) + textChunked[2].toLong()
    }

    return timeSec

}

fun Long.toTimerStringFormat(): String{
    var elapsedSeconds = this
    // Break the elapsed seconds into hours, minutes, and seconds.
    var hours: Long = 0
    var minutes: Long = 0
    var seconds: Long = 0
    if (elapsedSeconds >= 3600) {
        hours = elapsedSeconds / 3600
        elapsedSeconds -= hours * 3600
    }
    if (elapsedSeconds >= 60) {
        minutes = elapsedSeconds / 60
        elapsedSeconds -= minutes * 60
    }
    seconds = elapsedSeconds
    val sb = StringBuilder(8)
    val f = Formatter(sb, Locale.getDefault())
    return f.format("%1\$02d : %2\$02d : %3\$02d", hours, minutes, seconds).toString()
}