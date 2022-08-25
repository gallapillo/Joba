package com.gallapillo.joba.presentation.screens.create_resume

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.gallapillo.joba.common.Constants
import com.gallapillo.joba.common.Response
import com.gallapillo.joba.common.Screen
import com.gallapillo.joba.common.generateString
import com.gallapillo.joba.domain.model.Resume
import com.gallapillo.joba.domain.model.User
import com.gallapillo.joba.presentation.screens.profile.UserViewModel
import com.gallapillo.joba.presentation.theme.fontFamily
import java.util.*

@ExperimentalMaterialApi
@Composable
fun FirstStepCreateResumeScreen(
    navController: NavController,
    userViewModel: UserViewModel,
    resumeViewModel: ResumeViewModel
) {
    val expanded = remember {
        mutableStateOf(false)
    }
    val expandedSecond = remember {
        mutableStateOf(false)
    }
    val selectedWorkText = remember {
        mutableStateOf(Constants.WORK_FIRST_CATEGORY_LIST[0])
    }
    val selectedOptionText = remember {
        mutableStateOf(Constants.CATEGORY_LIST[0])
    }
    val indexSelected = remember {
        mutableStateOf(0)
    }
    val indexSelectedCategory = remember {
        mutableStateOf(0)
    }

    var user: User? = null

    when (val response = userViewModel.getUserData.value) {
        is Response.Loading -> {
            CircularProgressIndicator()
        }
        is Response.Success -> {
            user = response.data
        }
        is Response.Error -> {

        }
    }

    Column {
        Row(modifier = Modifier.height(56.dp).fillMaxWidth(), verticalAlignment = Alignment.CenterVertically){
            Icon(
                imageVector = Icons.Filled.Close,
                contentDescription = "previous step button",
                modifier = Modifier.clickable {
                    navController.navigate(Screen.ProfileScreen.route) {
                        popUpTo(Screen.FirstStepCreateResumeScreen.route) {
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
                    if (user != null) {
                        resumeViewModel.setResumeData(Resume(
                            user = user,
                            ownerId = user.userId,
                            name = "",
                            categoryId = indexSelected.value,
                            subCategoryId = indexSelectedCategory.value,
                            description = "",
                            id = generateString(Random(), "abcdefghijkmlonxyzpqwrtu", 11)
                        ))
                        navController.navigate(Screen.SecondStepCreateResumeScreen.route) {
                            popUpTo(Screen.FirstStepCreateResumeScreen.route) {
                                inclusive = true
                            }
                        }
                    }
                }.padding(end = 12.dp)
            )
        }
        Text(text = "Кем вы хотите работать?", fontSize = 32.sp, modifier = Modifier.padding(top = 16.dp, start = 16.dp))
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Шаг 1 из 3", fontSize = 12.sp, modifier = Modifier.padding(top = 16.dp, start = 16.dp))
        Spacer(modifier = Modifier.height(16.dp))
        ExposedDropdownMenuBox(
            expanded = expanded.value,
            onExpandedChange = {
                expanded.value = !expanded.value
            },
            modifier = Modifier.padding(start = 16.dp)
        ) {
            TextField(
                readOnly = true,
                value = selectedOptionText.value,
                onValueChange = { },
                label = { Text(
                    "Категория",
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Normal
                ) },
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
                Constants.CATEGORY_LIST.forEachIndexed { index, selectionOption ->
                    DropdownMenuItem(
                        onClick = {
                            selectedOptionText.value = selectionOption
                            when (index) {
                                0 -> {
                                    selectedWorkText.value = Constants.WORK_FIRST_CATEGORY_LIST[0]
                                }
                                1 -> {
                                    selectedWorkText.value = Constants.WORK_SECOND_CATEGORY_LIST[0]
                                }
                                2 -> {
                                    selectedWorkText.value = Constants.WORK_THIRD_CATEGORY_LIST[0]
                                }
                                3 -> {
                                    selectedWorkText.value = Constants.WORK_FOURTH_CATEGORY_LIST[0]
                                }
                                4 -> {
                                    selectedWorkText.value = Constants.WORK_FIFTH_CATEGORY_LIST[0]
                                }
                                else -> {
                                    selectedWorkText.value = Constants.WORK_FIRST_CATEGORY_LIST[0]
                                }
                            }
                            expanded.value = false
                            indexSelected.value = index
                        }
                    ) {
                        Text(text = selectionOption)
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        ExposedDropdownMenuBox(
            expanded = expandedSecond.value,
            onExpandedChange = {
                expandedSecond.value = !expandedSecond.value
            },
            modifier = Modifier.padding(start = 16.dp)
        ) {
            TextField(
                readOnly = true,
                value = selectedWorkText.value,
                onValueChange = { },
                label = { Text(
                    "Профессия",
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Normal
                ) },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(
                        expanded = expanded.value
                    )
                },
                colors = ExposedDropdownMenuDefaults.textFieldColors()
            )
            ExposedDropdownMenu(
                expanded = expandedSecond.value,
                onDismissRequest = {
                    expandedSecond.value = false
                }
            ) {
                when (indexSelected.value) {
                    0 -> {
                        Constants.WORK_FIRST_CATEGORY_LIST.forEachIndexed { index, selectionOption ->
                            DropdownMenuItem(
                                onClick = {
                                    selectedWorkText.value = selectionOption
                                    expandedSecond.value = false
                                    indexSelectedCategory.value = index
                                }
                            ) {
                                Text(text = selectionOption)
                            }
                        }
                    }
                    1 -> {
                        Constants.WORK_SECOND_CATEGORY_LIST.forEachIndexed { index, selectionOption ->
                            DropdownMenuItem(
                                onClick = {
                                    selectedWorkText.value = selectionOption
                                    expandedSecond.value = false
                                    indexSelectedCategory.value = index
                                }
                            ) {
                                Text(text = selectionOption)
                            }
                        }
                    }
                    2 -> {
                        Constants.WORK_THIRD_CATEGORY_LIST.forEachIndexed { index, selectionOption ->
                            DropdownMenuItem(
                                onClick = {
                                    selectedWorkText.value = selectionOption
                                    expandedSecond.value = false
                                    indexSelectedCategory.value = index
                                }
                            ) {
                                Text(text = selectionOption)
                            }
                        }
                    }
                    3 -> {
                        Constants.WORK_FOURTH_CATEGORY_LIST.forEachIndexed { index, selectionOption ->
                            DropdownMenuItem(
                                onClick = {
                                    selectedWorkText.value = selectionOption
                                    expandedSecond.value = false
                                    indexSelectedCategory.value = index
                                }
                            ) {
                                Text(text = selectionOption)
                            }
                        }
                    }
                    4 -> {
                        Constants.WORK_FIFTH_CATEGORY_LIST.forEachIndexed { index, selectionOption ->
                            DropdownMenuItem(
                                onClick = {
                                    selectedWorkText.value = selectionOption
                                    expandedSecond.value = false
                                    indexSelectedCategory.value = index
                                }
                            ) {
                                Text(text = selectionOption)
                            }
                        }
                    }
                    else -> {
                        Constants.WORK_FIFTH_CATEGORY_LIST.forEachIndexed { index, selectionOption ->
                            DropdownMenuItem(
                                onClick = {
                                    selectedWorkText.value = selectionOption
                                    expandedSecond.value = false
                                    indexSelectedCategory.value = index
                                }
                            ) {
                                Text(text = selectionOption)
                            }
                        }
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}