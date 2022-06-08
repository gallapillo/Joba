package com.gallapillo.joba.presentation.screens.profile

import android.widget.Toast
import android.widget.Toast.makeText
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.R
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.gallapillo.joba.common.Response
import com.gallapillo.joba.common.Screen
import com.gallapillo.joba.presentation.screens.auth_screen.AuthenticationViewModel
import com.gallapillo.joba.presentation.theme.fontFamily


@Composable
fun ProfileScreen(
    navController: NavController,
    userViewModel: UserViewModel,
    authenticationViewModel: AuthenticationViewModel
) {
    val context = LocalContext.current
    userViewModel.getUserInfo()

    when (val response = userViewModel.getUserData.value) {
        is Response.Loading -> {
            CircularProgressIndicator()
        }
        is Response.Success -> {
            if (response.data != null) {
                val user = response.data
                Column (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 24.dp)
                ) {
                    Row(horizontalArrangement = Arrangement.End) {
                        Icon(
                            imageVector = Icons.Default.ExitToApp,
                            contentDescription = "Sing out button",
                            modifier = Modifier.clickable {
                                authenticationViewModel.signOut()
                                when(val response = authenticationViewModel.signOutState.value) {
                                    is Response.Loading -> {

                                    }
                                    is Response.Error -> {
                                        makeText(context, response.message, Toast.LENGTH_LONG).show()
                                    }
                                    is Response.Success -> {
                                        if (response.data) {
                                            navController.navigate(Screen.HelloScreen.route) {
                                                popUpTo(Screen.ProfileScreen.route) {
                                                    inclusive = true
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        )
                    }
                    Spacer(modifier = Modifier.padding(top = 48.dp))
                    Text(text = user.name, fontSize = 48.sp)
                    Spacer(modifier = Modifier.padding(top = 12.dp))
                    Text(
                        text = "Ищю работу >",
                        fontFamily = fontFamily,
                        fontWeight = FontWeight.Normal
                    )
                    Spacer(modifier = Modifier.padding(top = 24.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Button(onClick = {  }) {
                            Text(
                                "Создать новое резюме",
                                fontFamily = fontFamily,
                                fontWeight = FontWeight.Normal
                            )
                        }
                    }
                }
            }
        }
        is Response.Error -> {
            makeText(context, "ERROR WITH RESPONSE", Toast.LENGTH_LONG).show()
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                Column (modifier = Modifier.weight(1f), horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "ERRPR WITH PROFILE")
                }
            }
        }
    }
}