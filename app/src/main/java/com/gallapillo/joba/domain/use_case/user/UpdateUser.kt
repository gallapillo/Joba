package com.gallapillo.joba.domain.use_case.user

import com.gallapillo.joba.domain.model.User
import com.gallapillo.joba.domain.repository.UserRepository
import javax.inject.Inject

class UpdateUser @Inject constructor(
    private val repository: UserRepository
) {
    operator fun invoke(user: User) = repository.saveUserChanges(user)
}