package com.example.tmdbapp.network

interface SessionHandler {
    fun putSessionId(sessionId: String)
    fun getSessionId(): String
}