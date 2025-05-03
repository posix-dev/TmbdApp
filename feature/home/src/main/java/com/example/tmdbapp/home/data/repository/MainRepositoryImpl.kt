package com.example.tmdbapp.home.data.repository

import com.example.tmdbapp.home.data.api.NowPlayingService
import com.example.tmdbapp.home.data.mapper.NowPlayingMapper
import com.example.tmdbapp.home.domain.repository.MainRepository
import com.example.tmdbapp.model.domain.NowPlayingMovie
import com.example.tmdbapp.model.domain.PopularMovie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val apiService: NowPlayingService,
    private val mapper: NowPlayingMapper
) : MainRepository {

    override fun getNowPlayingMovies(page: Int): Flow<NowPlayingMovie> {
        val result = apiService.getNowPlayingMovies(page).flowOn(Dispatchers.IO)

        return result.map(mapper::map)
    }

    override fun getPopular(page: Int): Flow<PopularMovie> {
        val result = apiService.getPopularMovies(page).flowOn(Dispatchers.IO)

        return result.map(mapper::map)
    }
}