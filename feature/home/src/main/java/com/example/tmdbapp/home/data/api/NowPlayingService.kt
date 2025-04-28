package com.example.tmdbapp.home.data.api

import com.example.tmdbapp.model.data.response.NowPlayingResponse
import com.example.tmdbapp.model.data.response.PopularResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NowPlayingService {
    @GET("movie/now_playing")
    fun getNowPlayingMovies(
        @Query("page")
        page: Int = 1
    ): Flow<NowPlayingResponse>

    @GET("movie/popular")
    fun getPopularMovies(
        @Query("page")
        page: Int = 1
    ): Flow<PopularResponse>
}