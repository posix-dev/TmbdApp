package com.example.tmdbapp.feature.profile.domain.interactor

import kotlinx.coroutines.flow.Flow

interface AuthInteractor {
    fun login(username: String, password: String): Flow<Unit>
    fun checkSessionId(): Flow<Boolean>
}