package com.example.tmdbapp.di

import com.example.tmdbapp.network.SessionHandler
import com.example.tmdbapp.prefs.SessionHandlerImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface SessionModule {
    @Singleton
    @Binds
    fun provideSessionHandler(impl: SessionHandlerImpl): SessionHandler
}