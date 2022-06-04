package com.gallapillo.joba.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.gallapillo.joba.common.Screen
import com.gallapillo.joba.presentation.screens.HelloScreen
import com.gallapillo.joba.presentation.screens.LoginScreen
import com.gallapillo.joba.presentation.theme.JobaTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JobaTheme {
                Surface(color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.HelloScreen.route
                    ) {
                        composable(route = Screen.HelloScreen.route) {
                            HelloScreen(navController)
                        }
                        composable(route = Screen.LoginScreen.route) {
                            LoginScreen(navController)
                        }
                        composable(route = Screen.RegisterScreen.route) {

                        }
                        composable(route = Screen.MainScreen.route) {

                        }
                    }
                }
            }
        }
    }
}

