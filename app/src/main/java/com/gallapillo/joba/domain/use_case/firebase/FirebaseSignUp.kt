package com.gallapillo.joba.domain.use_case.firebase

import com.gallapillo.joba.domain.repository.AuthenticationRepository
import javax.inject.Inject

class FirebaseSignUp @Inject constructor(
    private val repository: AuthenticationRepository
) {
    operator fun invoke(email: String, password: String, userName: String) = repository.firebaseSignUp(email, password, userName)
}