package com.example.tmbdapp.all_movies.domain

import com.example.tmdbapp.model.domain.NowPlayingMovie
import com.example.tmdbapp.model.domain.PopularMovie
import com.example.tmdbapp.model.domain.TopRatedMovie
import com.example.tmdbapp.model.domain.UpcomingMovie
import kotlinx.coroutines.flow.Flow

interface AllMoviesInteractor {
    fun getUpcomingMovies(): Flow<UpcomingMovie>
    fun getPopularMovies(): Flow<PopularMovie>
    fun getNowPlayingMovies(): Flow<NowPlayingMovie>
    fun getTopRatedMovies(): Flow<TopRatedMovie>
}