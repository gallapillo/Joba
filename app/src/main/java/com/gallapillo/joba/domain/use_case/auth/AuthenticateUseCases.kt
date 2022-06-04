package com.gallapillo.joba.domain.use_case.auth

import com.gallapillo.joba.domain.use_case.firebase.*

data class AuthenticateUseCases (
    val isUserAuthenticated: IsUserAuthenticated,
    val firebaseAuthState: FirebaseAuthState,
    val firebaseSignIn: FirebaseSignIn,
    val firebaseSignOut: FirebaseSignOut,
    val firebaseSignUp: FirebaseSignUp
) {
}