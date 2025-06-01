package com.example.tmdbapp.feature.profile.data.api

import com.example.tmdbapp.feature.profile.data.auth.CreateSessionRequest
import com.example.tmdbapp.feature.profile.data.auth.CreateSessionResponse
import com.example.tmdbapp.feature.profile.data.auth.RequestTokenResponse
import com.example.tmdbapp.feature.profile.data.auth.ValidateLoginRequest
import kotlinx.coroutines.flow.Flow
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface AuthService {
    @GET("authentication/token/new")
    fun getRequestToken(@Query("api_key") apiKey: String): Flow<RequestTokenResponse>

    @POST("authentication/token/validate_with_login")
    fun validateRequestToken(
        @Query("api_key") apiKey: String,
        @Body request: ValidateLoginRequest
    ): Flow<RequestTokenResponse>

    @POST("authentication/session/new")
    fun createSession(
        @Query("api_key") apiKey: String,
        @Body request: CreateSessionRequest
    ): Flow<CreateSessionResponse>
}