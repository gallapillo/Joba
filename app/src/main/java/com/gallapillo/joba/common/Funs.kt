package com.gallapillo.joba.common

import java.time.LocalDate
import java.time.Period
import kotlin.math.absoluteValue

fun isAdult(birthDate: LocalDate, currentDate: LocalDate): Boolean {
    val periodEighteenYears = Period.between(birthDate, currentDate)
    return periodEighteenYears.years.absoluteValue >= 18
}