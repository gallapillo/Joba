package com.gallapillo.joba.presentation.screens.profile

import android.widget.Toast
import android.widget.Toast.makeText
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.R
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.gallapillo.joba.common.Constants
import com.gallapillo.joba.common.Response
import com.gallapillo.joba.common.Screen
import com.gallapillo.joba.domain.model.Resume
import com.gallapillo.joba.domain.model.User
import com.gallapillo.joba.presentation.screens.auth_screen.AuthenticationViewModel
import com.gallapillo.joba.presentation.theme.BorderColor
import com.gallapillo.joba.presentation.theme.fontFamily
import kotlinx.coroutines.launch


@ExperimentalMaterialApi
@Composable
fun ProfileScreen(
    navController: NavController,
    userViewModel: UserViewModel,
    authenticationViewModel: AuthenticationViewModel
) {
    val context = LocalContext.current
    userViewModel.getUserInfo()
    val coroutineScope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)

    when (val response = userViewModel.getUserData.value) {
        is Response.Loading -> {
            CircularProgressIndicator()
        }
        is Response.Success -> {
            if (response.data != null) {
                val user = response.data

                val radioOptions = listOf("В активном поиске работы >", "Открыт к предложениям >", "Не ищю работу >")
                val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[user.wannaFindJob]) }
                var wannaJob = remember {
                    mutableStateOf(radioOptions[user.wannaFindJob])
                }
                ModalBottomSheetLayout(
                    sheetContent = {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(300.dp)
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(top = 12.dp)
                            ) {
                                Text(
                                    text = "Ваш статус поиска работы",
                                    modifier = Modifier.padding(start = 12.dp, bottom = 8.dp),
                                    fontFamily = fontFamily,
                                    fontWeight = FontWeight.Normal
                                )
                                radioOptions.forEachIndexed { index, text ->
                                    Row(
                                        Modifier
                                            .fillMaxWidth()
                                            .padding(horizontal = 16.dp)
                                    ) {
                                        RadioButton(
                                            selected = (text == selectedOption),modifier = Modifier.padding(all = Dp(value = 8F)),
                                            onClick = {
                                                onOptionSelected(text)
                                                wannaJob.value = text
                                                user.wannaFindJob = index
                                                userViewModel.setUserInfo(user)
                                                when (val response = userViewModel.setUserData.value) {
                                                    is Response.Loading -> {
                                                        makeText(
                                                            context,
                                                            "Пожалуйста подождите обновление данных",
                                                            Toast.LENGTH_LONG
                                                        ).show()
                                                    }
                                                    is Response.Error -> {
                                                        makeText(
                                                            context,
                                                            response.message,
                                                            Toast.LENGTH_LONG
                                                        ).show()
                                                    }
                                                    is Response.Success -> {
                                                        if (response.data) {
                                                            makeText(
                                                                context,
                                                                "Данные обновлены успешно!",
                                                                Toast.LENGTH_LONG
                                                            ).show()
                                                        }
                                                    }
                                                }
                                            }
                                        )
                                        Text(
                                            text = text,
                                            modifier = Modifier.padding(all = Dp(value = 8F)),
                                            fontFamily = fontFamily,
                                            fontWeight = FontWeight.Normal
                                        )
                                    }
                                }
                            }
                        }
                    },
                    sheetState = sheetState
                ) {

                    Row(modifier = Modifier.height(56.dp).fillMaxWidth(), verticalAlignment = Alignment.CenterVertically){
                        Spacer(Modifier.weight(1f).fillMaxHeight().padding(top = 12.dp, end = 12.dp))
                        Icon(
                            imageVector = Icons.Default.ExitToApp,
                            contentDescription = "Sign out button",
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
                            }.padding(end = 12.dp)
                        )
                    }

                    Column (
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 24.dp)
                    ) {

                        Spacer(modifier = Modifier.padding(top = 32.dp))
                        Text(
                            text = user.name,
                            fontFamily = fontFamily,
                            fontWeight = FontWeight.Normal,
                            fontSize = 48.sp
                        )
                        Spacer(modifier = Modifier.padding(top = 16.dp))
                        Text(
                            text = wannaJob.value,
                            fontFamily = fontFamily,
                            fontWeight = FontWeight.Normal,
                            modifier = Modifier.clickable {
                                coroutineScope.launch {
                                    sheetState.show()
                                }
                            }
                        )
                        if (user.resume.isEmpty()) {
                            EmptyResumeCard()
                        } else {
                            val resume = user.resume[0]
                            ResumeCard(resume)
                        }
                        Spacer(modifier = Modifier.padding(top = 48.dp))
                        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
                            Button(onClick = {
                                // Переход на другой экран создание резюме
                                navController.navigate(Screen.FirstStepCreateResumeScreen.route)
                            }) {
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
        }
        is Response.Error -> {
            makeText(context, "ERROR WITH RESPONSE", Toast.LENGTH_LONG).show()
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                Column (modifier = Modifier.weight(1f), horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "ERROR WITH PROFILE")
                }
            }
        }
    }
}

@Composable
fun ResumeCard(
    resume: Resume
) {

}

@Composable
fun EmptyResumeCard() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .padding(vertical = 32.dp, horizontal = 16.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(BorderColor)
    ) {
        Column(
            modifier = Modifier
                .padding(vertical = 96.dp, horizontal = 64.dp)
        ) {
            Text(
                text = "У вас отсутсвует резюме",
                fontFamily = fontFamily,
                fontWeight = FontWeight.Normal
            )
        }
    }
}

