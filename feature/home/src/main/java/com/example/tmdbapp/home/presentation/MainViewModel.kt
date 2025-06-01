package com.example.tmdbapp.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tmdbapp.home.domain.repository.MainRepository
import com.example.tmdbapp.home.presentation.MainViewModel.Event.*
import com.example.tmdbapp.model.domain.NowPlayingMovie
import com.example.tmdbapp.model.domain.PopularMovie
import com.example.tmdbapp.model.domain.Results
import com.example.tmdbapp.model.domain.TopRatedMovie
import com.example.tmdbapp.model.domain.UpcomingMovie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: MainRepository,
) : ViewModel() {

    private val state = MutableStateFlow(initState())

    private val events = MutableSharedFlow<Event>(
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_LATEST
    )

    private var pageCounter = 1

    fun getState(): StateFlow<MainState> = state.asStateFlow()
    fun getEvents(): SharedFlow<Event> = events.asSharedFlow()

    private fun initState() = MainState(
        isLoading = false,
        nowPlayingItems = listOf(),
        popularItems = listOf(),
        topRatedItems = listOf(),
        upcomingItems = listOf(),
        detailMovieId = 0
    )

    init {
        getWholeMovieList()
    }

    fun onAction(action: Action) {
        when (action) {
            is Action.OnClickedCard -> {
                viewModelScope.launch {
                    state.update { it.copy(detailMovieId = action.id) }
                    events.emit(GoToDetailScreen(action.id))
                }
            }

            is Action.OnAllWatchClicked -> {
                viewModelScope.launch {
                    events.emit(GoToAllMoviesScreen(action.category))
                }
            }
        }
    }

    data class TemporaryContainerList(
        val nowPlayingMovie: NowPlayingMovie,
        val popularMovies: PopularMovie,
        val topRatedMovie: TopRatedMovie,
        val upcomingMovie: UpcomingMovie,
    )

    private fun getWholeMovieList() {
        viewModelScope.launch {
            combine(
                getNowPlayingMovies(),
                getPopularMovies(),
                getTopRatedMovies(),
                getUpcomingMovies()
            ) { f1, f2, f3, f4 ->
                TemporaryContainerList(
                    nowPlayingMovie = f1,
                    popularMovies = f2,
                    topRatedMovie = f3,
                    upcomingMovie = f4
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
                            nowPlayingItems = temporaryContainer.nowPlayingMovie.results,
                            popularItems = temporaryContainer.popularMovies.results,
                            topRatedItems = temporaryContainer.topRatedMovie.results,
                            upcomingItems = temporaryContainer.upcomingMovie.results
                        )
                    }
                }
        }
    }

    private fun getPopularMovies(): Flow<PopularMovie> {
        return repository.getPopular(pageCounter)
    }

    private fun getTopRatedMovies(): Flow<TopRatedMovie> {
        return repository.getTopRatedMovies(pageCounter)
    }

    private fun getUpcomingMovies(): Flow<UpcomingMovie> {
        return repository.getUpcomingMovies(pageCounter)
    }

    private fun getNowPlayingMovies(): Flow<NowPlayingMovie> {
        return repository.getNowPlayingMovies(pageCounter)
    }

    data class MainState(
        val isLoading: Boolean,
        val nowPlayingItems: List<Results>,
        val popularItems: List<Results>,
        val topRatedItems: List<Results>,
        val upcomingItems: List<Results>,
        val detailMovieId: Int,
    )

    sealed interface Event {
        data class ShowError(val message: String) : Event
        data class GoToDetailScreen(val id: Int) : Event

        data class GoToAllMoviesScreen(val category: String) : Event
    }

    sealed interface Action {
        data class OnClickedCard(val id: Int) : Action

        data class OnAllWatchClicked(val category: String) : Action
    }
}