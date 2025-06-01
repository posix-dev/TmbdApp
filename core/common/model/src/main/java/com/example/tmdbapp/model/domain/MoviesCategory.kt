package com.example.tmdbapp.model.domain

enum class MoviesCategory(val category: String) {
    POPULAR("Popular"),
    NOW_PLAYING("Now_Playing"),
    UPCOMING("High_Rating"),
    HIGH_RATING("Top_Rated")
}