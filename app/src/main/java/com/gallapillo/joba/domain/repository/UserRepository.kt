package com.gallapillo.joba.domain.repository

import com.gallapillo.joba.common.Response
import com.gallapillo.joba.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    fun getUserById(userId: String) : Flow<Response<User>>

    fun getAllUsers(userId: String) : Flow<List<User>>

    fun saveUserChanges(user: User) : Flow<Response<Boolean>>
}