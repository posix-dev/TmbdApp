package com.example.tmbdapp.all_movies.di

import com.example.tmbdapp.all_movies.data.AllMoviesRepositoryImpl
import com.example.tmbdapp.all_movies.domain.AllMoviesInteractor
import com.example.tmbdapp.all_movies.domain.AllMoviesInteractorImpl
import com.example.tmbdapp.all_movies.domain.AllMoviesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class AllMoviesModule {
    @Binds
    abstract fun bindAllMoviesRepository(repoImpl: AllMoviesRepositoryImpl): AllMoviesRepository

    @Binds
    abstract fun bindAllMoviesInteractor(authRepositoryImpl: AllMoviesInteractorImpl): AllMoviesInteractor
}
