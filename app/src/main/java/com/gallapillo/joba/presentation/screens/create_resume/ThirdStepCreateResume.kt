package com.gallapillo.joba.presentation.screens.create_resume

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Check
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
import com.gallapillo.joba.common.Screen
import com.gallapillo.joba.domain.model.Education
import com.gallapillo.joba.domain.model.Experience
import com.gallapillo.joba.presentation.theme.fontFamily

@ExperimentalMaterialApi
@Composable
fun ThirdStepCreateResume(
    navController: NavController,
    resumeViewModel: ResumeViewModel
) {

    val openDialogExperience = remember { mutableStateOf(false) }
    val openDialogEducation = remember { mutableStateOf(false) }
    val nameEducation = remember { mutableStateOf("") }
    val expanded = remember { mutableStateOf(false) }
    val selectedTypeEducationText = remember { mutableStateOf(Constants.EDUCATION_LIST[0]) }
    val selectedTypeEducationIndex = remember { mutableStateOf(0) }

    var educationList = mutableListOf<Education>()
    var experienceList = mutableListOf<Experience>()

    Column {
        Row(modifier = Modifier.height(56.dp).fillMaxWidth(), verticalAlignment = Alignment.CenterVertically){
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "previous step button",
                modifier = Modifier.clickable {
                    navController.navigate(Screen.SecondStepCreateResumeScreen.route) {
                        popUpTo(Screen.ThirdStepCreateResumeScreen.route) {
                            inclusive = true
                        }
                    }
                }.padding(start = 12.dp)
            )
            Spacer(Modifier.weight(1f).fillMaxHeight().padding(top = 12.dp, end = 12.dp))
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = "next step button",
                modifier = Modifier.clickable {
                    navController.navigate(Screen.ProfileScreen.route) {
                        popUpTo(Screen.FirstStepCreateResumeScreen.route) {
                            inclusive = true
                        }
                        popUpTo(Screen.SecondStepCreateResumeScreen.route) {
                            inclusive = true
                        }
                        popUpTo(Screen.ThirdStepCreateResumeScreen.route) {
                            inclusive = true
                        }
                    }
                }.padding(end = 12.dp)
            )
        }
        Text(
            text = "Расскажите о своем опыте работы и образования",
            fontSize = 32.sp,
            modifier = Modifier.padding(top = 16.dp, start = 16.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Шаг 3 из 3", fontSize = 12.sp, modifier = Modifier.padding(top = 16.dp, start = 16.dp))
        Spacer(modifier = Modifier.height(16.dp))
        Row(modifier = Modifier.height(56.dp).fillMaxWidth(), verticalAlignment = Alignment.CenterVertically){
            Text(text = "Ваше образование", modifier = Modifier.padding(start = 12.dp))
            Spacer(Modifier.weight(1f).fillMaxHeight().padding(top = 12.dp, end = 12.dp))
            Text(
                text = "Добавить образование",
                modifier = Modifier
                    .padding(start = 12.dp)
                    .clickable {
                         openDialogExperience.value = true
                    }
            )
            // this need lazy column
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(modifier = Modifier.height(56.dp).fillMaxWidth(), verticalAlignment = Alignment.CenterVertically){
            Text(text = "Ваш опыт работы", modifier = Modifier.padding(start = 12.dp))
            Spacer(Modifier.weight(1f).fillMaxHeight().padding(top = 12.dp, end = 12.dp))
            Text(text = "Добавить место работы", modifier = Modifier.padding(start = 12.dp).clickable {
                openDialogEducation.value = true
            })
            // this need lazy column
        }

        if (openDialogEducation.value) {
            AlertDialog(
                onDismissRequest = {
                    openDialogEducation.value = false
                },
                title = {
                    Text(text = "Добавить образование")
                },
                text = {
                    Column() {
                        ExposedDropdownMenuBox(
                            expanded = expanded.value,
                            onExpandedChange = {
                                expanded.value = !expanded.value
                            }
                        ) {
                            TextField(
                                readOnly = true,
                                value = selectedTypeEducationText.value,
                                onValueChange = { },
                                label = { Text(
                                    "Тип образования",
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
                                Constants.GENDERS_LIST.forEachIndexed { index, selectionOption ->
                                    DropdownMenuItem(
                                        onClick = {
                                            selectedTypeEducationIndex.value = index
                                            selectedTypeEducationText.value = selectionOption
                                            expanded.value = false
                                        }
                                    ) {
                                        Text(text = selectionOption)
                                    }
                                }
                            }
                        }
                        TextField(
                            value = nameEducation.value,
                            onValueChange = { text ->
                                nameEducation.value = text
                            },
                            label = {
                                Text(text = "Описание")
                            },
                            modifier = Modifier.padding(start = 16.dp)
                        )
                    }
                },
                buttons = {
                    Row(
                        modifier = Modifier.padding(all = 8.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Button(
                            modifier = Modifier.fillMaxWidth(),
                            onClick = {
                                educationList.add(Education(
                                    selectedTypeEducationIndex.value,
                                    "21 - 21",
                                    nameEducation.value
                                ))
                                openDialogEducation.value = false
                            }
                        ) {
                            Text("Добавить")
                        }
                    }
                }
            )
        }

        if (openDialogExperience.value) {
            AlertDialog(
                onDismissRequest = {
                    openDialogExperience.value = false
                },
                title = {
                    Text(text = "Добавить образование")
                },
                text = {
                    Column() {
                        TextField(
                            value = nameEducation.value,
                            onValueChange = { text ->
                                nameEducation.value = text
                            },
                            label = {
                                Text(text = "Описание")
                            },
                            modifier = Modifier.padding(start = 16.dp)
                        )
                    }
                },
                buttons = {
                    Row(
                        modifier = Modifier.padding(all = 8.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Button(
                            modifier = Modifier.fillMaxWidth(),
                            onClick = { openDialogExperience.value = false }
                        ) {
                            Text("Dismiss")
                        }
                    }
                }
            )
        }
    }
}
