package com.gallapillo.joba.presentation.screens.auth_screen

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.gallapillo.joba.common.Response
import com.gallapillo.joba.common.Screen
import com.gallapillo.joba.presentation.screens.auth_screen.AuthenticationViewModel

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: AuthenticationViewModel
) {
    Box(modifier= Modifier.fillMaxSize()) {

        val emailState = remember {
            mutableStateOf("")
        }
        val passwordState = remember {
            mutableStateOf("")
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Вход в учетную запись",
                modifier = Modifier.padding(10.dp),
                fontSize = 30.sp,
                fontFamily = FontFamily.SansSerif
            )
            OutlinedTextField(
                value = emailState.value,
                onValueChange = {
                    emailState.value = it
                },
                modifier = Modifier.padding(10.dp),
                label = {
                    Text(text = "Email")
                }
            )
            OutlinedTextField(
                value = passwordState.value,
                onValueChange = {
                    passwordState.value = it
                },
                modifier = Modifier.padding(10.dp),
                label = {
                    Text(text = "Пароль")
                },
                visualTransformation = PasswordVisualTransformation()
            )
            Button(
                onClick = {
                    viewModel.signIn(
                        email = emailState.value,
                        password = passwordState.value
                    )
                },
                modifier = Modifier.padding(8.dp)
            ) {
                Text(
                    text = "Войти"
                )
                when (val response = viewModel.signInState.value) {
                    is Response.Loading -> {
                        CircularProgressIndicator(
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                    is Response.Error -> {
                        Toast.makeText(LocalContext.current, response.message, Toast.LENGTH_LONG).show()
                    }
                    is Response.Success -> {
                        if (response.data) {
                            navController.navigate(Screen.MainScreen.route) {
                                popUpTo(Screen.LoginScreen.route) {
                                    inclusive = true
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}