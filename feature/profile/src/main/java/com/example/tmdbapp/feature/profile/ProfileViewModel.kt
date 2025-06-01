package com.example.tmdbapp.feature.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tmdbapp.feature.profile.domain.interactor.AuthInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val interactor: AuthInteractor
) : ViewModel() {

    private val state = MutableStateFlow(initState())

    fun getState(): StateFlow<ProfileState> = state.asStateFlow()

    private fun initState() = ProfileState(
        isLoading = false,
        isAuth = false,
        login = "",
        password = ""
    )

    init {
        checkLogin()
    }

    private fun checkLogin() {
        viewModelScope.launch {
            interactor
                .checkSessionId()
                .collect { isAuthenticated ->
                    state.update { it.copy(isAuth = isAuthenticated) }
                }
        }
    }

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
        val value = state.value
        viewModelScope.launch {
            interactor
                .login(value.login, value.password)
                .onStart {
                    state.update { it.copy(isLoading = true) }
                }
                .catch {
                    println("fffffuccck")
                }
                .onCompletion {
                    state.update { it.copy(isLoading = false) }
                }
                .collect {
                    state.update { it.copy(isAuth = true) }
                }
        }
    }

    data class ProfileState(
        val isLoading: Boolean,
        val isAuth: Boolean,
        val login: String,
        val password: String
    )
}
