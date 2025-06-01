package com.example.tmdbapp.detail_movie.data.mapper

import com.example.tmdbapp.detail_movie.data.response.BelongsToCollectionResponse
import com.example.tmdbapp.detail_movie.data.response.CastResponse
import com.example.tmdbapp.detail_movie.data.response.CrewResponse
import com.example.tmdbapp.detail_movie.data.response.DetailMovieResponse
import com.example.tmdbapp.detail_movie.data.response.GenresResponse
import com.example.tmdbapp.detail_movie.data.response.MovieCreditsResponse
import com.example.tmdbapp.detail_movie.data.response.ProductionCompaniesResponse
import com.example.tmdbapp.detail_movie.data.response.ProductionCountriesResponse
import com.example.tmdbapp.detail_movie.data.response.SpokenLanguagesResponse
import com.example.tmdbapp.detail_movie.domain.BelongsToCollection
import com.example.tmdbapp.detail_movie.domain.Cast
import com.example.tmdbapp.detail_movie.domain.CreditMovie
import com.example.tmdbapp.detail_movie.domain.Crew
import com.example.tmdbapp.detail_movie.domain.DetailMovie
import com.example.tmdbapp.detail_movie.domain.Genres
import com.example.tmdbapp.detail_movie.domain.ProductionCompanies
import com.example.tmdbapp.detail_movie.domain.ProductionCountries
import com.example.tmdbapp.detail_movie.domain.SpokenLanguages
import javax.inject.Inject

class DetailMovieMapper @Inject constructor() {
    
    fun map(from: MovieCreditsResponse): CreditMovie = CreditMovie(
        cast = from.cast.map { it.map() },
        crew = from.crew.map { it.map() },
        id = from.id ?: 0
    )

    private fun CastResponse.map(): Cast {
        return Cast(
            adult = adult ?: false,
            gender = gender ?: 0,
            id = id ?: 0,
            knownForDepartment = knownForDepartment.orEmpty(),
            name = name.orEmpty(),
            originalName = originalName.orEmpty(),
            popularity = popularity ?: 0.toDouble(),
            profilePath = profilePath,
            castId = castId ?: 0,
            character = character.orEmpty(),
            creditId = creditId.orEmpty(),
            order = order ?: 0
        )
    }

    private fun CrewResponse.map(): Crew {
        return Crew(
            adult = adult ?: false,
            gender = gender ?: 0,
            id = id ?: 0,
            knownForDepartment = knownForDepartment.orEmpty(),
            name = name.orEmpty(),
            originalName = originalName.orEmpty(),
            popularity = popularity ?: 0.toDouble(),
            profilePath = profilePath.orEmpty(),
            creditId = creditId.orEmpty(),
            department = department.orEmpty(),
            job = job.orEmpty()
        )
    }

    fun map(from: DetailMovieResponse): DetailMovie {
        return DetailMovie(
            adult = from.adult ?: false,
            backdropPath = from.backdropPath.orEmpty(),
            belongsToCollection = from.belongsToCollection?.map(),
            budget = from.budget,
            genres = from.genres.orEmpty().map { it.map() },
            homepage = from.homepage.orEmpty(),
            id = from.id,
            imdbId = from.imdbId,
            originCountry = from.originCountry.orEmpty(),
            originalLanguage = from.originalLanguage,
            originalTitle = from.originalTitle,
            overview = from.overview,
            popularity = from.popularity,
            posterPath = from.posterPath,
            productionCompanies = from.productionCompanies.orEmpty().map {
                it.map()
            },
            productionCountries = from.productionCountries.orEmpty().map {
                it.map()
            },
            releaseDate = from.releaseDate,
            revenue = from.revenue,
            runtime = from.runtime,
            spokenLanguages = from.spokenLanguages.orEmpty().map {
                it.map()
            },
            status = from.status,
            tagline = from.tagline,
            title = from.title,
            video = from.video,
            voteAverage = from.voteAverage,
            voteCount = from.voteCount
        )
    }

    private fun BelongsToCollectionResponse.map(): BelongsToCollection? {
        return BelongsToCollection(
            id = id,
            name = name,
            posterPath = posterPath,
            backdropPath = backdropPath
        )
    }
}

private fun GenresResponse.map(): Genres {
    return Genres(
        id = id,
        name = name
    )
}

private fun ProductionCompaniesResponse.map(): ProductionCompanies {
    return ProductionCompanies(
        id = id,
        logoPath = logoPath.orEmpty(),
        name = name,
        originCountry = originCountry
    )
}

private fun ProductionCountriesResponse.map(): ProductionCountries {
    return ProductionCountries(
        iso31661 = iso31661,
        name = name,
    )
}

private fun SpokenLanguagesResponse.map(): SpokenLanguages {
    return SpokenLanguages(
        englishName = englishName,
        iso6391 = iso6391,
        name = name,
    )
}
