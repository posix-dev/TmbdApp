package com.example.tmdbapp.feature.profile.data.auth

import com.google.gson.annotations.SerializedName

data class CreateSessionRequest(
    @SerializedName("request_token")
    val requestToken: String
)