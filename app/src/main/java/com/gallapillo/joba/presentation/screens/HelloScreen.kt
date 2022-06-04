package com.gallapillo.joba.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.gallapillo.joba.R
import com.gallapillo.joba.common.Screen
import com.gallapillo.joba.presentation.theme.TextColor

@Composable
fun HelloScreen(
    navController: NavController
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.padding(top = 48.dp))
        Image(
            painter = painterResource(id = R.drawable.ic_hero_image),
            contentDescription = "Logo Image",
        )
        Spacer(modifier = Modifier.padding(top = 64.dp))
        Text(
            "Найдите удаленную работу вашей мечты",
            modifier = Modifier.padding(start = 20.dp, end = 20.dp),
            color = TextColor,
            fontSize = 24.sp
        )
        Spacer(modifier = Modifier.padding(top = 12.dp))
        Text(
            "Создайте резюме или отправьте отклик на вакансию в компанию с мировым именем и рабоатайте по свободному графику",
            modifier = Modifier.padding(start = 20.dp, end = 20.dp),
            color = TextColor
        )
        Spacer(modifier = Modifier.padding(top = 96.dp))
        Row {
            Button(
                modifier = Modifier.padding(start = 20.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.White,
                    contentColor = TextColor
                ),
                onClick = {
                    navController.navigate(Screen.LoginScreen.route)
                }
            ) {
                Text("Войти")
            }
            Button(
                modifier = Modifier.padding(start = 20.dp),
                onClick = {
                    navController.navigate(Screen.RegisterScreen.route)
                }
            ) {
                Text("Регистрация")
            }
        }
    }
}