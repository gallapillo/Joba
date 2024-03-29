package com.gallapillo.joba.common

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.currentBackStackEntryAsState
import java.time.LocalDate
import java.time.Period
import java.util.*
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

fun NavOptionsBuilder.popUpToTop(navController: NavController) {
    popUpTo(navController.currentBackStackEntry?.destination?.route ?: return) {
        inclusive =  true
    }
}

fun generateString(rng: Random, characters: String, length: Int): String {
    val text = CharArray(length)
    for (i in 0 until length) {
        text[i] = characters[rng.nextInt(characters.length)]
    }
    return String(text)
}