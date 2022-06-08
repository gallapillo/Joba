package com.gallapillo.joba.domain.use_case.user

import com.gallapillo.joba.domain.repository.UserRepository
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class GetUser @Inject constructor(
    private val repository: UserRepository
) {
    operator fun invoke(userId: String) = repository.getUserById(userId)
}