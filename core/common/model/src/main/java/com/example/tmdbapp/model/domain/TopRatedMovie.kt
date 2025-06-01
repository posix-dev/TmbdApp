package com.example.tmdbapp.model.domain

data class TopRatedMovie(
    val page: Int,
    val results: List<Results>,
    val totalPages: Int,
    val totalResults: Int
)
