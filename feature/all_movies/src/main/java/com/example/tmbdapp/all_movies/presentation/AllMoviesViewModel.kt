package com.example.tmbdapp.all_movies.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tmbdapp.all_movies.domain.AllMoviesInteractor
import com.example.tmdbapp.model.domain.MoviesCategory
import com.example.tmdbapp.model.domain.TopRatedMovie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AllMoviesViewModel @Inject constructor(
    private val interactor: AllMoviesInteractor,
) : ViewModel() {
    private val state = MutableStateFlow(initState())

    private val events = MutableSharedFlow<Event>(
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_LATEST
    )

    fun getState(): StateFlow<AllMoviesState> = state.asStateFlow()


    fun getEvents(): SharedFlow<Event> = events.asSharedFlow()

    private fun initState() = AllMoviesState(
        isLoading = false,
        movies = null
    )

    fun init(category: String) {
        val categoryEnum = MoviesCategory.entries.find { it.category  == category }

        when (categoryEnum) {
            MoviesCategory.POPULAR -> getPopularMovies()
            MoviesCategory.NOW_PLAYING -> getNowPlayingMovies()
            MoviesCategory.UPCOMING -> getUpcomingMovies()
            MoviesCategory.HIGH_RATING -> getTopRatedMovies()
            else -> throw IllegalArgumentException("Не корректный тип")
        }
    }

    private fun getPopularMovies() {
        viewModelScope.launch {
            interactor.getPopularMovies()
                .onStart {
                    state.update { it.copy(isLoading = true) }
                }
                .onCompletion {
                    state.update { it.copy(isLoading = false) }
                }
                .catch {}
                .collect { movies ->
                    state.update {
                        it.copy(
                            movies = TopRatedMovie(
                                page = movies.page,
                                results = movies.results,
                                totalPages = movies.totalPages,
                                totalResults = movies.totalResults
                            )
                        )
                    }
                }
        }
    }

    private fun getUpcomingMovies() {
        viewModelScope.launch {
            interactor.getUpcomingMovies()
                .onStart {
                    state.update { it.copy(isLoading = true) }
                }
                .onCompletion {
                    state.update { it.copy(isLoading = false) }
                }
                .catch {}
                .collect { movies ->
                    state.update {
                        it.copy(
                            movies = TopRatedMovie(
                                page = movies.page,
                                results = movies.results,
                                totalPages = movies.totalPages,
                                totalResults = movies.totalResults
                            )
                        )
                    }
                }
        }
    }

    private fun getNowPlayingMovies() {
        viewModelScope.launch {
            interactor.getNowPlayingMovies()
                .onStart {
                    state.update { it.copy(isLoading = true) }
                }
                .onCompletion {
                    state.update { it.copy(isLoading = false) }
                }
                .catch {}
                .collect { movies ->
                    state.update {
                        it.copy(
                            movies = TopRatedMovie(
                                page = movies.page,
                                results = movies.results,
                                totalPages = movies.totalPages,
                                totalResults = movies.totalResults
                            )
                        )
                    }
                }
        }
    }

    private fun getTopRatedMovies() {
        viewModelScope.launch {
            interactor.getTopRatedMovies()
                .onStart {
                    state.update { it.copy(isLoading = true) }
                }
                .onCompletion {
                    state.update { it.copy(isLoading = false) }
                }
                .catch {}
                .collect { movies ->
                    state.update {
                        it.copy(movies = movies)
                    }
                }
        }
    }

    fun onAction(action: Action) {
        when (action) {
            is Action.OnCardClicked -> {
                events.tryEmit(Event.GoToDetailScreen(action.id))
            }
        }
    }

    data class AllMoviesState(
        val isLoading: Boolean,
        val movies: TopRatedMovie?,
    )

    sealed interface Action {
        data class OnCardClicked(val id: Int) : Action
    }

    sealed interface Event {
        data class GoToDetailScreen(val id: Int) : Event
    }

}