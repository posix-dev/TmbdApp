package com.example.tmdbapp.detail_movie.data.response

import com.google.gson.annotations.SerializedName

data class ProductionCompaniesResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("logo_path")
    val logoPath: String?,
    @SerializedName("name")
    val name: String,
    @SerializedName("origin_country")
    val originCountry: String,
)
