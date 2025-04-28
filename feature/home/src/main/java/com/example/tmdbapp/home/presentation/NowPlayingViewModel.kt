package com.example.tmdbapp.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tmdbapp.home.domain.repository.MainRepository
import com.example.tmdbapp.model.domain.NowPlayingMovie
import com.example.tmdbapp.model.domain.PopularMovie
import com.example.tmdbapp.model.domain.Results
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NowPlayingViewModel @Inject constructor(
    private val repository: MainRepository
) : ViewModel() {

    private val state = MutableStateFlow(initState())
    private val events = MutableSharedFlow<Event>()

    private var pageCounter = 1

    fun getState(): StateFlow<MainState> = state.asStateFlow()
    fun getEvents(): SharedFlow<Event> = events.asSharedFlow()

    private fun initState() = MainState(
        isLoading = false,
        nowPlayingItems = listOf(),
        popularItems = listOf(),
    )

    init {
        getWholeMovieList()
    }

    data class TemporaryContainerList(
        val nowPlayingMovies: NowPlayingMovie,
        val popularMovies: PopularMovie,
    )

    private fun getWholeMovieList() {
        viewModelScope.launch {
            getNowPlayingMovies().zip(getPopularMovies()) { f1, f2 ->
                TemporaryContainerList(
                    nowPlayingMovies = f1,
                    popularMovies = f2
                )
            }
                .onStart {
                    state.update { it.copy(isLoading = true) }
                }
                .onCompletion {
                    state.update { it.copy(isLoading = false) }
                }
                .catch { exc ->
                    events.emit(Event.ShowError("Помогите"))
                }
                .collect { temporaryContainer ->
                    state.update {
                        it.copy(
                            nowPlayingItems = temporaryContainer.nowPlayingMovies.results,
                            popularItems = temporaryContainer.popularMovies.results
                        )
                    }
                }
        }
    }

    private fun getPopularMovies(): Flow<PopularMovie> {
        return repository.getPopular(pageCounter)
    }

    private fun getNowPlayingMovies(): Flow<NowPlayingMovie> {
        return repository.getNowPlayingMovies(pageCounter)
    }

    data class MainState(
        val isLoading: Boolean,
        val nowPlayingItems: List<Results>,
        val popularItems: List<Results>,
    )

    sealed interface Event {
        data class ShowError(val message: String) : Event
    }

    sealed interface Action {
        data object ScrollToEndList : Action
    }
}