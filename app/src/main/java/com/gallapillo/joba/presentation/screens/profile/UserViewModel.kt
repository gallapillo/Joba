package com.gallapillo.joba.presentation.screens.profile

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gallapillo.joba.common.Response
import com.gallapillo.joba.domain.model.User
import com.gallapillo.joba.domain.use_case.user.GetUser
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val auth: FirebaseAuth,
    private val getUser: GetUser
) : ViewModel() {

    private val userid = auth.currentUser?.uid
    private val _getUserData = mutableStateOf<Response<User?>>(Response.Success(null))
    val getUserData: State<Response<User?>> = _getUserData

    private val _setUserData = mutableStateOf<Response<Boolean>>(Response.Success(false))
    val setUserData : State<Response<Boolean>> = _setUserData

    fun getUserInfo() {
        if (userid != null) {
            viewModelScope.launch {
                getUser.invoke(userId = userid).collect {
                    _getUserData.value = it
                }
            }
        }
    }
}