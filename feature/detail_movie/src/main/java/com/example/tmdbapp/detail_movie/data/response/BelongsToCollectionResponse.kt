package com.example.tmdbapp.detail_movie.data.response

import com.google.gson.annotations.SerializedName

data class BelongsToCollectionResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("poster_path") val posterPath: String,
    @SerializedName("backdrop_path") val backdropPath: String
)