package com.example.tmdbapp.detail_movie.domain.repository

import com.example.tmdbapp.detail_movie.domain.CreditMovie
import com.example.tmdbapp.detail_movie.domain.DetailMovie
import kotlinx.coroutines.flow.Flow

interface DetailMovieRepository {
    fun getDetailInfo(id: Int): Flow<DetailMovie>
    fun getMovieCredits(id: Int): Flow<CreditMovie>
}