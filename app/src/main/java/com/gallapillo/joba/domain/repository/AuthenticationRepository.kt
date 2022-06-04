package com.gallapillo.joba.domain.repository

import com.gallapillo.joba.common.Response
import kotlinx.coroutines.flow.Flow

interface AuthenticationRepository {
    fun isUserAuthenticatedInFirebase() : Boolean

    fun getFirebaseAuthState() : Flow<Boolean>

    fun firebaseSignIn(email: String, password: String): Flow<Response<Boolean>>

    fun firebaseSignOut(): Flow<Response<Boolean>>

    fun firebaseSignUp(email: String, password: String, userName: String): Flow<Response<Boolean>>
}