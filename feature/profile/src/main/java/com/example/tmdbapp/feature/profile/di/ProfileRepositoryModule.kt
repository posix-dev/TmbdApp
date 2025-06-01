package com.example.tmdbapp.feature.profile.di

import com.example.tmdbapp.feature.profile.data.repository.AuthRepositoryImpl
import com.example.tmdbapp.feature.profile.domain.interactor.AuthInteractor
import com.example.tmdbapp.feature.profile.domain.interactor.AuthInteractorImpl
import com.example.tmdbapp.feature.profile.domain.repository.AuthRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class AuthModule {
    @Binds
    abstract fun bindAuthRepository(authRepositoryImpl: AuthRepositoryImpl): AuthRepository

    @Binds
    abstract fun bindAuthInteractor(authRepositoryImpl: AuthInteractorImpl): AuthInteractor
}