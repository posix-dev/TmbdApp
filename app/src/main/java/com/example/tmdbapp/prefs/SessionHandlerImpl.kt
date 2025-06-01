package com.example.tmdbapp.prefs

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.example.tmdbapp.network.SessionHandler
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class SessionHandlerImpl @Inject constructor(
    @ApplicationContext
    private val context: Context
): SessionHandler {
        private val prefs: SharedPreferences
            get() {
                return context.getSharedPreferences(SESSION_PREFS, MODE_PRIVATE)
            }

    override fun putSessionId(sessionId: String) {
        prefs.edit().apply {
            putString(SESSION_KEY, sessionId)
            apply()
        }
    }

    override fun getSessionId(): String {
        return prefs.getString(SESSION_KEY, "") ?: ""
    }

    private companion object {
        const val SESSION_PREFS = "SESSION_PREFS"
        const val SESSION_KEY = "SESSION_KEY"
    }
}