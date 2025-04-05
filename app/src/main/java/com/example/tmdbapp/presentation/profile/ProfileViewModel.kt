package com.example.tmdbapp.presentation.profile

import androidx.lifecycle.ViewModel
import com.example.tmdbapp.presentation.MainViewModel.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject


@HiltViewModel
class ProfileViewModel @Inject constructor(

) : ViewModel() {

    private val state = MutableStateFlow(initState())
    private val events = MutableSharedFlow<Event>()

    fun getState(): StateFlow<ProfileState> = state.asStateFlow()
    fun getEvents(): SharedFlow<Event> = events.asSharedFlow()

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
