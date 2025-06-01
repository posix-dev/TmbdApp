package com.example.tmdbapp.detail_movie.di

import com.example.tmdbapp.detail_movie.domain.repository.DetailMovieRepository
import com.example.tmdbapp.detail_movie.data.repository.DetailMovieRepositoryImpl
import com.example.tmdbapp.detail_movie.data.api.DetailMovieService
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
internal interface DetailMovieModule {
    @Binds
    fun bindDetailMovieRepository(authRepositoryImpl: DetailMovieRepositoryImpl): DetailMovieRepository

    companion object {

        @Provides
        fun provideAuthenticateService(retrofit: Retrofit): DetailMovieService =
            retrofit.create(DetailMovieService::class.java)
    }
}