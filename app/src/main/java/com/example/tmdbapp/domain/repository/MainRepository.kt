package com.example.tmdbapp.domain.repository

import com.example.tmdbapp.domain.entity.NowPlaying

interface MainRepository {
    suspend fun getData(): NowPlaying
}