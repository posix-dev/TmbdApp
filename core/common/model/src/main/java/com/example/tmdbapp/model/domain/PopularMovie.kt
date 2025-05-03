package com.example.tmdbapp.model.domain

data class PopularMovie(
    val page: Int,
    val results: List<Results>,
    val totalPages: Int,
    val totalResults: Int
)
