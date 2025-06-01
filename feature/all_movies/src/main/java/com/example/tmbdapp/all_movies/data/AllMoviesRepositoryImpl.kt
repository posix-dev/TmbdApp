package com.example.tmbdapp.all_movies.data

import com.example.tmbdapp.all_movies.domain.AllMoviesRepository
import com.example.tmdbapp.model.domain.NowPlayingMovie
import com.example.tmdbapp.model.domain.PopularMovie
import com.example.tmdbapp.model.domain.TopRatedMovie
import com.example.tmdbapp.model.domain.UpcomingMovie
import com.example.tmdbapp.network.api.MovieService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AllMoviesRepositoryImpl @Inject constructor(
    private val api: MovieService,
    private val mapper: MoviesMapper
) : AllMoviesRepository {

    override fun getUpcomingMovies(): Flow<UpcomingMovie> {
        val result = api.getUpcomingMovies().flowOn(Dispatchers.IO)

        return result.map(mapper::map)
    }

    override fun getPopularMovies(): Flow<PopularMovie> {
        val result = api.getPopularMovies().flowOn(Dispatchers.IO)

        return result.map(mapper::map)
    }

    override fun getNowPlayingMovies(): Flow<NowPlayingMovie> {
        val result = api.getNowPlayingMovies().flowOn(Dispatchers.IO)

        return result.map(mapper::map)
    }

    override fun getTopRatedMovies(): Flow<TopRatedMovie> {
        val result = api.getTopRatedMovies().flowOn(Dispatchers.IO)

        return result.map(mapper::map)
    }
}