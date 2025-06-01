package com.example.tmdbapp.detail_movie.domain

data class CreditMovie(
    val cast: List<Cast>,
    val crew: List<Crew>,
    val id: Int,
)

data class Cast(
    val adult: Boolean,
    val gender: Int,
    val id: Int,
    val knownForDepartment: String,
    val name: String,
    val originalName: String,
    val popularity: Double,
    val profilePath: String?,
    val castId: Int,
    val character: String,
    val creditId: String,
    val order: Int,
)

data class Crew(
    val adult: Boolean,
    val gender: Int,
    val id: Int,
    val knownForDepartment: String,
    val name: String,
    val originalName: String,
    val popularity: Double,
    val profilePath: String?,
    val creditId: String,
    val department: String,
    val job: String,
)