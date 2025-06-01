package com.example.tmbdapp.detail_actor.domain

data class DetailPerson(
    val adult: Boolean,
    val alsoKnownAs: ArrayList<String> = arrayListOf(),
    val biography: String,
    val birthday: String,
    val deathday: String,
    val gender: Int?,
    val homepage: String,
    val id: Int?,
    val imdbId: String?,
    val knownForDepartment: String,
    val name: String,
    val placeOfBirth: String,
    val popularity: Double?,
    val profilePath: String?,
)
