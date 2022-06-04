package com.gallapillo.joba.presentation.screens.auth_screen

import android.widget.Toast
import android.widget.Toast.makeText
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
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
fun RegisterScreen(
    navController: NavController,
    viewModel: AuthenticationViewModel
) {
    Box(modifier= Modifier.fillMaxSize()) {

        val nameState = remember {
            mutableStateOf("")
        }
        val surNameState = remember {
            mutableStateOf("")
        }
        val emailState = remember {
            mutableStateOf("")
        }
        val passwordState = remember {
            mutableStateOf("")
        }
        val confirmPasswordState = remember {
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
                text = "Регистрация пользователя",
                modifier = Modifier.padding(10.dp),
                fontSize = 30.sp,
                fontFamily = FontFamily.SansSerif
            )
            OutlinedTextField(
                value = nameState.value,
                onValueChange = {
                    nameState.value = it
                },
                modifier = Modifier.padding(10.dp),
                label = {
                    Text(text = "Имя")
                }
            )
            OutlinedTextField(
                value = surNameState.value,
                onValueChange = {
                    surNameState.value = it
                },
                modifier = Modifier.padding(10.dp),
                label = {
                    Text(text = "Фамилия")
                }
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
            OutlinedTextField(
                value = confirmPasswordState.value,
                onValueChange = {
                    confirmPasswordState.value = it
                },
                modifier = Modifier.padding(10.dp),
                label = {
                    Text(text = "Повторите пароль")
                },
                visualTransformation = PasswordVisualTransformation()
            )
            Button(
                onClick = {
                    viewModel.signUp(
                        email = emailState.value,
                        password = passwordState.value,
                        userName = nameState.value
                    )
                },
                modifier = Modifier.padding(8.dp)
            ) {
                Text(
                    text = "Зарегистрироватся"
                )
                when (val response = viewModel.signUpState.value) {
                    is Response.Loading -> {
                        CircularProgressIndicator(
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                    is Response.Error -> {
                        makeText(LocalContext.current, response.message, Toast.LENGTH_LONG).show()
                    }
                    is Response.Success -> {
                        if (response.data) {
                            navController.navigate(Screen.MainScreen.route) {
                                popUpTo(Screen.RegisterScreen.route) {
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