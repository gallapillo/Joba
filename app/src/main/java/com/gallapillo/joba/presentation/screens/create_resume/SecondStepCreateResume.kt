package com.gallapillo.joba.presentation.screens.create_resume

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.gallapillo.joba.common.Screen
import com.gallapillo.joba.domain.model.Resume

@ExperimentalMaterialApi
@Composable
fun SecondStepCreateResume(
    navController: NavController,
    resumeViewModel: ResumeViewModel
) {
    val descriptionText = remember {
        mutableStateOf("")
    }

    val wannaSalaryNumber = remember {
        mutableStateOf("")
    }

    val context = LocalContext.current

    Column {
        Row(modifier = Modifier.height(56.dp).fillMaxWidth(), verticalAlignment = Alignment.CenterVertically){
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "previous step button",
                modifier = Modifier.clickable {
                    navController.navigate(Screen.FirstStepCreateResumeScreen.route) {
                        popUpTo(Screen.SecondStepCreateResumeScreen.route) {
                            inclusive = true
                        }
                    }
                }.padding(start = 12.dp)
            )
            Spacer(Modifier.weight(1f).fillMaxHeight().padding(top = 12.dp, end = 12.dp))
            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = "next step button",
                modifier = Modifier.clickable {
                    if (descriptionText.value.isNotEmpty()) {
                        if (wannaSalaryNumber.value.isNotEmpty()) {
                            if (resumeViewModel.resumeData.value.user != null) {
                                resumeViewModel.setResumeData(
                                    Resume(
                                        user = resumeViewModel.resumeData.value.user,
                                        ownerId = resumeViewModel.resumeData.value.user!!.userId,
                                        name = resumeViewModel.resumeData.value.name,
                                        categoryId = resumeViewModel.resumeData.value.categoryId,
                                        subCategoryId = resumeViewModel.resumeData.value.subCategoryId,
                                        description = descriptionText.value,
                                        id = resumeViewModel.resumeData.value.id,
                                        wannaSalary = wannaSalaryNumber.value
                                    )
                                )
                            }
                            navController.navigate(Screen.ThirdStepCreateResumeScreen.route) {
                                popUpTo(Screen.SecondStepCreateResumeScreen.route) {
                                    inclusive = true
                                }
                            }
                        } else {
                            Toast.makeText(context, "Введите желаемую запрплату", Toast.LENGTH_LONG).show()
                        }
                    } else {
                        Toast.makeText(context, "Введите описание", Toast.LENGTH_LONG).show()
                    }
                }.padding(end = 12.dp)
            )
        }
        Text(
            text = "Расскажите о себе",
            fontSize = 32.sp,
            modifier = Modifier.padding(top = 16.dp, start = 16.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Шаг 2 из 3", fontSize = 12.sp, modifier = Modifier.padding(top = 16.dp, start = 16.dp))
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = descriptionText.value,
            onValueChange = { text ->
                descriptionText.value = text
            },
            label = {
                Text(text = "Описание")
            },
            modifier = Modifier.padding(start = 16.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = wannaSalaryNumber.value,
            onValueChange = { text ->
                wannaSalaryNumber.value = text
            },
            label = {
                Text(text = "Желаемая зарплата в рублях")
            },
            modifier = Modifier.padding(start = 16.dp),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            )
        )
    }
    Spacer(modifier = Modifier.height(16.dp))
}
