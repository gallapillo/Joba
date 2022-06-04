package com.gallapillo.joba.di

import com.gallapillo.joba.data.firebase.AuthenticationRepositoryImpl
import com.gallapillo.joba.domain.repository.AuthenticationRepository
import com.gallapillo.joba.domain.use_case.auth.AuthenticateUseCases
import com.gallapillo.joba.domain.use_case.firebase.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideFirebaseAuthentication(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

    @Singleton
    @Provides
    fun provideFireBaseFireStore(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

    @Singleton
    @Provides
    fun provideFireBaseStorage(): FirebaseStorage {
        return FirebaseStorage.getInstance()
    }

    @Singleton
    @Provides
    fun provideAuthenticationRepository(auth: FirebaseAuth, firestore: FirebaseFirestore): AuthenticationRepository {
        return AuthenticationRepositoryImpl(auth = auth, firestore = firestore)
    }

    @Singleton
    @Provides
    fun provideAuthenticateUseCases(repository: AuthenticationRepositoryImpl) : AuthenticateUseCases {
        return AuthenticateUseCases(
            isUserAuthenticated = IsUserAuthenticated(repository = repository),
            firebaseAuthState = FirebaseAuthState(repository = repository),
            firebaseSignOut = FirebaseSignOut(repository),
            firebaseSignIn = FirebaseSignIn(repository),
            firebaseSignUp = FirebaseSignUp(repository)
        )
    }
}