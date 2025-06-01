package com.example.tmdbapp.feature.profile.data.auth

import com.google.gson.annotations.SerializedName

data class CreateSessionResponse(
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("session_id")
    val sessionId: String
)