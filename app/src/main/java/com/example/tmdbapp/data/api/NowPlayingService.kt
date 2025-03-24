package com.example.tmdbapp.data.api

import com.example.tmdbapp.data.response.NowPlayingResponse
import retrofit2.http.GET

interface NowPlayingService {
    @GET("movie/now_playing")
    suspend fun getData(): NowPlayingResponse
}