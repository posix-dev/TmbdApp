package com.example.tmdbapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tmdbapp.domain.repository.MainRepository
import com.example.tmdbapp.domain.entity.Results
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: MainRepository
): ViewModel() {

    private val state = MutableStateFlow(initState())
    private val events = MutableSharedFlow<Event>()

    fun getState(): StateFlow<MainState> = state.asStateFlow()
    fun getEvents(): SharedFlow<Event> = events.asSharedFlow()

    private fun initState() = MainState(
        isLoading = false,
        items = emptyList()
    )

    init {
        getData()
    }

    private fun getData() {
        viewModelScope.launch {
            state.update { it.copy(isLoading = true) }

            withContext(Dispatchers.IO) {
                val data = repository.getData()
                state.update { it.copy(items = data.results) }
            }

            state.update { it.copy(isLoading = false) }
        }
    }

    data class MainState(
        val isLoading: Boolean,
        val items: List<Results>
    )

    sealed interface Event {

    }
}