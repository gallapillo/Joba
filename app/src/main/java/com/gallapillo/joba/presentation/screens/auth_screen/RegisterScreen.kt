package com.gallapillo.joba.presentation.screens.auth_screen

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.widget.DatePicker
import android.widget.Toast
import android.widget.Toast.makeText
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.gallapillo.joba.common.Constants
import com.gallapillo.joba.common.Response
import com.gallapillo.joba.common.Screen
import com.gallapillo.joba.domain.model.User
import com.gallapillo.joba.presentation.screens.auth_screen.AuthenticationViewModel
import java.util.*

@ExperimentalMaterialApi
@Composable
fun RegisterScreen(
    navController: NavController,
    viewModel: AuthenticationViewModel
) {
    Box(modifier= Modifier.fillMaxSize()) {

        val context = LocalContext.current

        val calendar = Calendar.getInstance()

        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val days = calendar.get(Calendar.DAY_OF_MONTH)

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
        val birthDayState = remember {
            mutableStateOf("")
        }
        val expanded = remember {
            mutableStateOf(false)
        }
        val selectedOptionText = remember {
            mutableStateOf(Constants.GENDERS_LIST[0])
        }

        val datePickerDialog = DatePickerDialog(
            context,
            { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
                birthDayState.value = "$mDayOfMonth/${mMonth+1}/$mYear"
            }, year, month, days
        )

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
            // TODO:(gallapillo) maybe upgrade a birth day update registration form
            Text(text = "Дата рождения: ${birthDayState.value}", fontSize = 14.sp, textAlign = TextAlign.Center)
            Button(
                onClick = {
                    datePickerDialog.show()
                }
            ) {
                Text(text = "Выбрать дату рождения")
            }
            // GENDER CHOSE
            ExposedDropdownMenuBox(
                expanded = expanded.value,
                onExpandedChange = {
                    expanded.value = !expanded.value
                }
            ) {
                TextField(
                    readOnly = true,
                    value = selectedOptionText.value,
                    onValueChange = { },
                    label = { Text("Ваш пол") },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(
                            expanded = expanded.value
                        )
                    },
                    colors = ExposedDropdownMenuDefaults.textFieldColors()
                )
                ExposedDropdownMenu(
                    expanded = expanded.value,
                    onDismissRequest = {
                        expanded.value = false
                    }
                ) {
                    Constants.GENDERS_LIST.forEach { selectionOption ->
                        DropdownMenuItem(
                            onClick = {
                                selectedOptionText.value = selectionOption
                                expanded.value = false
                            }
                        ) {
                            Text(text = selectionOption)
                        }
                    }
                }
            }
            //
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
                       User(
                           email = emailState.value,
                           password = passwordState.value,
                           name = nameState.value,
                           surName = surNameState.value,
                           gender = selectedOptionText.value,
                           birthDay = birthDayState.value,
                       )
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
