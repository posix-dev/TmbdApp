package com.example.tmdbapp.detail_movie.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tmdbapp.detail_movie.domain.CreditMovie
import com.example.tmdbapp.detail_movie.domain.repository.DetailMovieRepository
import com.example.tmdbapp.detail_movie.presentation.model.PresentationDetailMovie
import com.example.tmdbapp.extension.formattedYear
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
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class DetailMovieViewModel @Inject constructor(
    private val repository: DetailMovieRepository,
) : ViewModel() {

    private val state = MutableStateFlow(initState())
    private val events = MutableSharedFlow<Event>(
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_LATEST
    )


    fun getEvents(): SharedFlow<Event> = events.asSharedFlow()

    fun getState(): StateFlow<DetailMovieState> = state.asStateFlow()

    private fun initState() = DetailMovieState(
        isLoading = false,
        detailMovie = null,
        movieCredits = null
    )

    fun init(id: Int) {
        viewModelScope.launch {
            repository.getDetailInfo(id)
                .zip(repository.getMovieCredits(id)) { f1, f2 ->
                    f1 to f2
                }
                .onStart {
                    state.update { it.copy(isLoading = true) }
                }
                .onCompletion {
                    state.update { it.copy(isLoading = false) }
                }
                .catch {
                    println("hello ${it.message}")
                }
                .collect { pair ->
                    val (value, movieCredits) = pair
                    state.update {
                        it.copy(
                            detailMovie = PresentationDetailMovie(
                                backdropPath = value.backdropPath,
                                belongsToCollection = value.belongsToCollection,
                                budget = value.budget,
                                genres = value.genres,
                                homepage = value.homepage,
                                id = value.id,
                                imdbId = value.imdbId,
                                originCountry = value.originCountry,
                                originalLanguage = value.originalLanguage,
                                originalTitle = value.originalTitle,
                                overview = value.overview,
                                popularity = value.popularity,
                                posterPath = value.posterPath,
                                productionCompanies = value.productionCompanies,
                                productionCountries = value.productionCountries,
                                titleYear = "(${value.releaseDate?.formattedYear()})",
                                releaseDate = value.releaseDate,
                                revenue = value.revenue,
                                runtime = value.runtime,
                                spokenLanguages = value.spokenLanguages,
                                status = value.status,
                                tagline = value.tagline,
                                title = value.title,
                                video = value.video,
                                voteAverage = value.voteAverage,
                                voteCount = value.voteCount,
                                adult = value.adult
                            ),
                            movieCredits = movieCredits
                        )
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

    sealed interface Action {
        data class OnCardClicked(val id: Int) : Action
    }

    sealed interface Event {
        data class GoToDetailScreen(val id: Int) : Event
    }

    data class DetailMovieState(
        val isLoading: Boolean,
        val detailMovie: PresentationDetailMovie?,
        val movieCredits: CreditMovie?,
    )

}