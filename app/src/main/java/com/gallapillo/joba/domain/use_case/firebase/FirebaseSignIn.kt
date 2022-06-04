package com.gallapillo.joba.domain.use_case.firebase


import com.gallapillo.joba.domain.repository.AuthenticationRepository
import javax.inject.Inject

class FirebaseSignIn @Inject constructor(
    private val repository: AuthenticationRepository
) {
    operator fun invoke(email: String, password: String) = repository.firebaseSignIn(email, password)
}