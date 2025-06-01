package com.example.tmdbapp.home.domain.repository

import com.example.tmdbapp.model.domain.NowPlayingMovie
import com.example.tmdbapp.model.domain.PopularMovie
import com.example.tmdbapp.model.domain.TopRatedMovie
import com.example.tmdbapp.model.domain.UpcomingMovie
import kotlinx.coroutines.flow.Flow

interface MainRepository {
    fun getNowPlayingMovies(page: Int): Flow<NowPlayingMovie>
    fun getPopular(pageCounter: Int): Flow<PopularMovie>
    fun getTopRatedMovies(page: Int): Flow<TopRatedMovie>
    fun getUpcomingMovies(page: Int): Flow<UpcomingMovie>
}