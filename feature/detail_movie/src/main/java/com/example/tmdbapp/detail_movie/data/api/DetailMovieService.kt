package com.example.tmdbapp.detail_movie.data.api

import com.example.tmdbapp.detail_movie.data.response.DetailMovieResponse
import com.example.tmdbapp.detail_movie.data.response.MovieCreditsResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

internal interface DetailMovieService {
    @GET("movie/{movieId}")
    fun getDetailInfo(
        @Path("movieId")
        id: Int,
        @Query("append_to_response")
        appendToResponse: String = "",
        @Query("language")
        language: String = "ru"
    ): Flow<DetailMovieResponse>

    @GET("movie/{movieId}/credits")
    fun getMovieCredits(
        @Path("movieId") movieId: Int,
        @Query("language")
        language: String = "ru"
    ): Flow<MovieCreditsResponse>

}