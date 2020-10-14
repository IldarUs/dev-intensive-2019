package ru.skillbranch.devintensive.extensions

import java.lang.IllegalStateException
import java.text.SimpleDateFormat
import java.util.*

const val SECOND = 1000L
const val MINUTE = SECOND * 60
const val HOUR = MINUTE * 60
const val DAY = HOUR * 24

fun Date.format(pattern:String="HH:mm:ss dd.MM.yy"):String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value:Int, units: TimeUnits):Date {
    var time = this.time

    time += when (units) {
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
    }
    this.time = time
    return this
}

fun Date.humanizeDiff(date: Date = Date()) : String {
    var result: String = ""
    val curDate: Date = Date()

    result = when(date) {
                in curDate..curDate.add(1, TimeUnits.SECOND) -> "Только что"
                in curDate..curDate.add(45, TimeUnits.SECOND) -> "несколько секунд назад"
                in curDate..curDate.add(75, TimeUnits.SECOND) -> "минуту назад"
                in curDate..curDate.add(45, TimeUnits.MINUTE) -> "N минут назад"
                in curDate..curDate.add(75, TimeUnits.MINUTE) -> "час назад"
                in curDate..curDate.add(22, TimeUnits.HOUR) -> "N часов назад"
                in curDate..curDate.add(26, TimeUnits.HOUR) -> "день назад"
                in curDate..curDate.add(360, TimeUnits.DAY) -> "N дней назад"
                else -> "более года назад"
    }
    return result
}

enum class TimeUnits{
    SECOND,
    MINUTE,
    HOUR,
    DAY
}