package com.gallapillo.joba.data.firebase

import com.gallapillo.joba.common.Constants
import com.gallapillo.joba.common.Response
import com.gallapillo.joba.domain.model.Resume
import com.gallapillo.joba.domain.model.User
import com.gallapillo.joba.domain.repository.ResumeRepository
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ResumeRepositoryImpl @Inject constructor (
    private val firebaseFirestore: FirebaseFirestore
) : ResumeRepository {

    private var operationSuccessful = false

    override fun addResumeForUser(resume: Resume, user: User): Flow<Response<Boolean>> = flow {
        operationSuccessful = false
        try {
            user.resume.add(resume)
            val userObj = mutableMapOf<String, Any>()
            userObj["resume"] = user.resume
            firebaseFirestore.collection(Constants.USERS_COLLECTION).document(user.userId).update(userObj as Map<String, Any>)
                .addOnSuccessListener {
                    operationSuccessful = true
                }.await()
            if (operationSuccessful) {
                emit(Response.Success(operationSuccessful))
            } else {
                emit(Response.Error("Edit error"))
            }
        } catch (e: Exception) {
            Response.Error(e.localizedMessage ?: "Unexcepted error ocurred")
        }
    }

    override fun getResumeForUser(userId: String) {
        
    }

}