package com.gallapillo.joba.common

sealed class Screen(val route: String) {
    object HelloScreen : Screen("hello_screen")
    object LoginScreen : Screen("login_screen")
    object RegisterScreen : Screen("register_screen")
    object MainScreen : Screen("main_screen")
}
