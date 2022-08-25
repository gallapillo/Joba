package com.gallapillo.joba.presentation.screens.create_resume

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gallapillo.joba.common.Response
import com.gallapillo.joba.domain.model.Resume
import com.gallapillo.joba.domain.model.User
import com.gallapillo.joba.domain.use_case.resume.AddResume
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ResumeViewModel @Inject constructor(
    private val addResume: AddResume
) : ViewModel() {
    private val _resumeData = mutableStateOf(Resume(id = "", user = null, description = "", ownerId = "", categoryId = 0, subCategoryId = 0, name = ""))
    val resumeData: State<Resume> = _resumeData

    private val _addResumeData = mutableStateOf<Response<Boolean>>(Response.Success(false))
    val addResumeData : State<Response<Boolean>> = _addResumeData

    fun setResumeData(resume: Resume) {
        viewModelScope.launch {
            _resumeData.value = resume
        }
    }

    fun addResumeToUser(resume: Resume, user: User) {
        viewModelScope.launch {
            addResume.invoke(resume, user).collect {
                _addResumeData.value = it
            }
        }
    }
}