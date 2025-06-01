package com.example.tmdbapp.detail_movie.data.response

import com.google.gson.annotations.SerializedName

data class SpokenLanguagesResponse(
    @SerializedName("english_name") val englishName: String,
    @SerializedName("iso_639_1") val iso6391: String,
    @SerializedName("name") val name: String
)
