package com.example.tmbdapp.detail_actor.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tmbdapp.detail_actor.domain.DetailPerson
import com.example.tmbdapp.detail_actor.domain.PeopleRepository
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
class DetailActorViewModel @Inject constructor(
    private val repository: PeopleRepository
) : ViewModel() {

    private val state = MutableStateFlow(initState())

    fun getState(): StateFlow<DetailActorState> = state.asStateFlow()

    private fun initState() = DetailActorState(
        isLoading = false,
        person = null
    )

    fun init(id: Int) {
        viewModelScope.launch {
            repository.getPersonDetails(id)
                .onStart {
                    state.update { it.copy(isLoading = true) }
                }
                .onCompletion {
                    state.update { it.copy(isLoading = false) }
                }
                .catch {}
                .collect { person ->
                    state.update { it.copy(person = person) }
                }
        }
    }

    data class DetailActorState(
        val isLoading: Boolean,
        val person: DetailPerson?
    )
}