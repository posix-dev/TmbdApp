package com.example.tmbdapp.all_movies.domain

import com.example.tmdbapp.model.domain.NowPlayingMovie
import com.example.tmdbapp.model.domain.PopularMovie
import com.example.tmdbapp.model.domain.TopRatedMovie
import com.example.tmdbapp.model.domain.UpcomingMovie
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AllMoviesInteractorImpl @Inject constructor(
    private val repository: AllMoviesRepository
): AllMoviesInteractor {

    override fun getUpcomingMovies(): Flow<UpcomingMovie> {
        return repository.getUpcomingMovies()
    }

    override fun getPopularMovies(): Flow<PopularMovie> {
        return repository.getPopularMovies()
    }

    override fun getNowPlayingMovies(): Flow<NowPlayingMovie> {
        return repository.getNowPlayingMovies()
    }

    override fun getTopRatedMovies(): Flow<TopRatedMovie> {
        return repository.getTopRatedMovies()
    }

}