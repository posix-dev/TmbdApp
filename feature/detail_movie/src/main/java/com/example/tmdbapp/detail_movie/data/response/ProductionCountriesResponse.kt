package com.example.tmdbapp.detail_movie.data.response

import com.google.gson.annotations.SerializedName

data class ProductionCountriesResponse(
    @SerializedName("iso_3166_1") val iso31661: String,
    @SerializedName("name") val name: String
)
