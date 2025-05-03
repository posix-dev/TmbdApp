package com.example.tmdbapp.home.domain.repository

import com.example.tmdbapp.model.domain.NowPlayingMovie
import com.example.tmdbapp.model.domain.PopularMovie
import kotlinx.coroutines.flow.Flow

interface MainRepository {
    fun getNowPlayingMovies(page: Int): Flow<NowPlayingMovie>
    fun getPopular(pageCounter: Int): Flow<PopularMovie>
}