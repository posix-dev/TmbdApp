package com.example.tmdbapp.detail_movie.data.repository

import com.example.tmdbapp.detail_movie.data.api.DetailMovieService
import com.example.tmdbapp.detail_movie.data.mapper.DetailMovieMapper
import com.example.tmdbapp.detail_movie.domain.CreditMovie
import com.example.tmdbapp.detail_movie.domain.DetailMovie
import com.example.tmdbapp.detail_movie.domain.repository.DetailMovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class DetailMovieRepositoryImpl @Inject constructor(
    private val api: DetailMovieService,
    private val mapper: DetailMovieMapper,
//    private val localeManager: CustomLocaleManager
): DetailMovieRepository {

    override fun getDetailInfo(id: Int): Flow<DetailMovie> {
        return api.getDetailInfo(id)
            .map(mapper::map)
            .flowOn(Dispatchers.IO)
    }

    override fun getMovieCredits(id: Int): Flow<CreditMovie> {
        return api.getMovieCredits(id)
            .map(mapper::map)
            .flowOn(Dispatchers.IO)
    }
}