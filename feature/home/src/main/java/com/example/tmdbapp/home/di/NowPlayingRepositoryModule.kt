package com.example.tmdbapp.home.di

import com.example.tmdbapp.home.data.repository.MainRepositoryImpl
import com.example.tmdbapp.home.domain.repository.MainRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class NowPlayingRepositoryModule {
    @Binds
    abstract fun bindRepository(impl: MainRepositoryImpl): MainRepository
}