package com.gallapillo.joba.domain.repository

import com.gallapillo.joba.common.Response
import com.gallapillo.joba.domain.model.Resume
import com.gallapillo.joba.domain.model.User
import kotlinx.coroutines.flow.Flow

interface ResumeRepository {

    fun addResumeForUser(resume: Resume, user: User) : Flow<Response<Boolean>>

    fun getResumeForUser(userId: String)
}