package com.example.tmdbapp.data.repository

import com.example.tmdbapp.data.mapper.NowPlayingMapper
import com.example.tmdbapp.data.api.NowPlayingService
import com.example.tmdbapp.domain.repository.MainRepository
import com.example.tmdbapp.domain.entity.NowPlaying
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val apiService: NowPlayingService,
    private val mapper: NowPlayingMapper
): MainRepository {

    override suspend fun getData(): NowPlaying {
        val response = apiService.getData()
        return mapper.map(response)
    }
}