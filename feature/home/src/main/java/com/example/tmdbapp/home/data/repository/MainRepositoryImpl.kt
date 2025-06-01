package com.example.tmdbapp.home.data.repository

import com.example.tmdbapp.network.api.MovieService
import com.example.tmdbapp.home.data.mapper.MainMapper
import com.example.tmdbapp.home.domain.repository.MainRepository
import com.example.tmdbapp.model.domain.NowPlayingMovie
import com.example.tmdbapp.model.domain.PopularMovie
import com.example.tmdbapp.model.domain.TopRatedMovie
import com.example.tmdbapp.model.domain.UpcomingMovie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val apiService: MovieService,
    private val mapper: MainMapper
) : MainRepository {

    override fun getNowPlayingMovies(page: Int): Flow<NowPlayingMovie> {
        val result = apiService.getNowPlayingMovies(page).flowOn(Dispatchers.IO)

        return result.map(mapper::map)
    }

    override fun getPopular(page: Int): Flow<PopularMovie> {
        val result = apiService.getPopularMovies(page).flowOn(Dispatchers.IO)

        return result.map(mapper::map)
    }

    override fun getTopRatedMovies(page: Int): Flow<TopRatedMovie> {
        val result = apiService.getTopRatedMovies(page).flowOn(Dispatchers.IO)

        return result.map(mapper::map)
    }

    override fun getUpcomingMovies(page: Int): Flow<UpcomingMovie> {
        val result = apiService.getUpcomingMovies(page).flowOn(Dispatchers.IO)

        return result.map(mapper::map)
    }
}