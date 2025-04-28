package com.example.tmdbapp.feature.profile

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject


@HiltViewModel
class ProfileViewModel @Inject constructor(

) : ViewModel() {

    private val state = MutableStateFlow(initState())

    fun getState(): StateFlow<ProfileState> = state.asStateFlow()

    private fun initState() = ProfileState(
        isLoading = false,
        login = "",
        password = ""
    )

    fun updatePassword(text: String) {
        // todo validation of password
        state.update {
            it.copy(password = text)
        }
    }

    fun updateLogin(text: String) {
        // todo validation of login
        state.update {
            it.copy(login = text)
        }
    }

    fun onLoginClick() {
        // go to repo/interactor
    }

    data class ProfileState(
        val isLoading: Boolean,
        val login: String,
        val password: String
    )
}
