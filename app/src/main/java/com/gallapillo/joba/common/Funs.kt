package com.gallapillo.joba.common

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import java.time.LocalDate
import java.time.Period
import kotlin.math.absoluteValue

fun isAdult(birthDate: LocalDate, currentDate: LocalDate): Boolean {
    val periodEighteenYears = Period.between(birthDate, currentDate)
    return periodEighteenYears.years.absoluteValue >= 18
}

@Composable
fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}