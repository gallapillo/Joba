package com.gallapillo.joba.domain.use_case.resume

import com.gallapillo.joba.domain.model.Resume
import com.gallapillo.joba.domain.model.User
import com.gallapillo.joba.domain.repository.ResumeRepository
import javax.inject.Inject

class AddResume @Inject constructor(
    private val repository: ResumeRepository
) {
    operator fun invoke(resume: Resume, user: User) = repository.addResumeForUser(resume = resume, user = user)
}