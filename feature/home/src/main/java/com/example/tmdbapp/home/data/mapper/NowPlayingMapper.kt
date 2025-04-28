package com.example.tmdbapp.home.data.mapper

import com.example.tmdbapp.model.domain.Dates
import com.example.tmdbapp.model.domain.NowPlayingMovie
import com.example.tmdbapp.model.domain.Results
import com.example.tmdbapp.model.data.response.NowPlayingResponse
import com.example.tmdbapp.model.data.response.DatesResponse
import com.example.tmdbapp.model.data.response.PopularResponse
import com.example.tmdbapp.model.data.response.ResultsResponse
import com.example.tmdbapp.model.domain.PopularMovie
import javax.inject.Inject

class NowPlayingMapper @Inject constructor() {

    fun map(response: PopularResponse): PopularMovie = response.run {
        return PopularMovie(
            page = page,
            results = results.map { it.map() },
            totalPages = totalPages,
            totalResults = totalResults
        )
    }

    fun map(response: NowPlayingResponse): NowPlayingMovie = response.run {
        return NowPlayingMovie(
            dates = dates.map(),
            page = page,
            results = results.map { it.map() },
            totalPages = totalPages,
            totalResults = totalResults
        )
    }

    private fun DatesResponse.map(): Dates {
        return Dates(
            maximum = maximum,
            minimum = minimum
        )
    }

    private fun ResultsResponse.map(): Results {
        return Results(
            adult = adult,
            backdropPath = backdropPath,
            genreIds = genreIds,
            id = id,
            originalLanguage = originalLanguage,
            originalTitle = originalTitle,
            overview = overview,
            popularity = popularity,
            posterPath = posterPath,
            releaseDate = releaseDate,
            title = title,
            video = video,
            voteAverage = voteAverage,
            voteCount = voteCount,
        )
    }
}
