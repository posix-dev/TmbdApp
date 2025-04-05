package com.example.tmdbapp.data.repository

import com.example.tmdbapp.core.network.responseHandler
import com.example.tmdbapp.data.api.NowPlayingService
import com.example.tmdbapp.data.mapper.NowPlayingMapper
import com.example.tmdbapp.data.response.NowPlayingResponse
import com.example.tmdbapp.domain.entity.NowPlaying
import com.example.tmdbapp.domain.repository.MainRepository
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val apiService: NowPlayingService,
    private val mapper: NowPlayingMapper
) : MainRepository {

    override suspend fun getData(): com.example.tmdbapp.core.network.Result<NowPlaying> {
        val result = responseHandler { apiService.getData() }

        return when (result) {
            is com.example.tmdbapp.core.network.Result.Success<NowPlayingResponse> -> {
                com.example.tmdbapp.core.network.Result.Success(mapper.map(result.data))
            }

            else -> com.example.tmdbapp.core.network.Result.Error(code = 500, message = "")
        }
    }
}