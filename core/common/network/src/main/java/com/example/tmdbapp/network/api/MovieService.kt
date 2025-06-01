package com.example.tmdbapp.network.api

import com.example.tmdbapp.model.data.response.NowPlayingResponse
import com.example.tmdbapp.model.data.response.PopularResponse
import com.example.tmdbapp.model.data.response.TopRatedResponse
import com.example.tmdbapp.model.data.response.UpcomingResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    // todo сделать feature для определения страны, откуда заходим для language
    @GET("movie/now_playing")
    fun getNowPlayingMovies(
        @Query("page") page: Int = 1,
        @Query("language") language: String? = "ru",
    ): Flow<NowPlayingResponse>

    @GET("movie/popular")
    fun getPopularMovies(
        @Query("page") page: Int = 1,
        @Query("language") language: String? = "ru",
    ): Flow<PopularResponse>

    @GET("movie/top_rated")
    fun getTopRatedMovies(
        @Query("page") page: Int = 1,
        @Query("language") language: String? = "ru",
    ): Flow<TopRatedResponse>

    @GET("movie/upcoming")
    fun getUpcomingMovies(
        @Query("page") page: Int = 1,
        @Query("language") language: String? = "ru",
    ): Flow<UpcomingResponse>
}