package com.example.tmdbapp.feature.profile.domain.interactor

import com.example.tmdbapp.feature.profile.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AuthInteractorImpl @Inject constructor(
    private val repository: AuthRepository
): AuthInteractor {

    override fun login(username: String, password: String): Flow<Unit> {
        return repository.login(username, password)
    }

    override fun checkSessionId(): Flow<Boolean> {
        return repository.checkSessionId()
    }
}