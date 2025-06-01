package com.example.tmdbapp.feature.profile.data.repository

import com.example.tmdbapp.feature.profile.data.api.AuthService
import com.example.tmdbapp.feature.profile.data.auth.CreateSessionRequest
import com.example.tmdbapp.feature.profile.data.auth.ValidateLoginRequest
import com.example.tmdbapp.feature.profile.domain.repository.AuthRepository
import com.example.tmdbapp.network.SessionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val api: AuthService,
    private val apiKey: String,
    private val sessionHandler: SessionHandler
) : AuthRepository {

    override fun checkSessionId(): Flow<Boolean> {
        return flowOf(sessionHandler.getSessionId().isNotEmpty())
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun login(username: String, password: String): Flow<Unit> {
        return api.getRequestToken(apiKey)
            .flatMapConcat { tokenResponse ->
                api.validateRequestToken(
                    apiKey,
                    ValidateLoginRequest(username, password, tokenResponse.requestToken)
                )
            }
            .flatMapConcat { validatedTokenResponse ->
                api
                    .createSession(
                        apiKey,
                        CreateSessionRequest(validatedTokenResponse.requestToken)
                    )
                    .map { session ->
                        sessionHandler.putSessionId(session.sessionId)
                    }
            }
            .flowOn(Dispatchers.IO)
//        val validatedTokenResponse = api.validateRequestToken(
//            apiKey,
//            ValidateLoginRequest(username, password, tokenResponse.requestToken)
//        )
//        val session =
//            api.createSession(apiKey, CreateSessionRequest(validatedTokenResponse.requestToken))
//        sessionHandler.putSessionId(session.sessionId)
    }
}