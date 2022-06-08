package com.gallapillo.joba.data.firebase

import com.gallapillo.joba.common.Constants.USERS_COLLECTION
import com.gallapillo.joba.common.Response
import com.gallapillo.joba.domain.model.User
import com.gallapillo.joba.domain.repository.UserRepository
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject


class UserRepositoryImpl @Inject constructor (
    private val firebaseFirestore: FirebaseFirestore
) : UserRepository {

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun getUserById(userId: String): Flow<Response<User>> = callbackFlow {
        Response.Loading
        val snapshotListener = firebaseFirestore.collection(USERS_COLLECTION)
            .document(userId)
            .addSnapshotListener { snapshot, error ->
                val response = if (snapshot != null) {
                    val userInfo = snapshot.toObject(User::class.java)
                    Response.Success<User>(userInfo!!)
                } else {
                    Response.Error(error?.message?:error.toString())
                }
                trySend(response).isSuccess
            }
        awaitClose {
            snapshotListener.remove()
        }
    }

    override fun getAllUsers(userId: String): Flow<List<User>>  = callbackFlow {

    }
}