package com.example.tmdbapp.detail_movie.data.response

import com.google.gson.annotations.SerializedName

data class GenresResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String
)
