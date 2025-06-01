package com.example.tmdbapp.feature.profile.domain.repository

import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun login(username: String, password: String): Flow<Unit>
    fun checkSessionId(): Flow<Boolean>
}