package com.example.tmdbapp.model.domain

data class UpcomingMovie(
    val dates: Dates,
    val page: Int,
    val results: List<Results>,
    val totalPages: Int,
    val totalResults: Int
)
