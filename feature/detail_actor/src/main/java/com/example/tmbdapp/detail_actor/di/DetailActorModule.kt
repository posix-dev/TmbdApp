package com.example.tmbdapp.detail_actor.di

import com.example.tmbdapp.detail_actor.data.PeopleRepositoryImpl
import com.example.tmbdapp.detail_actor.data.api.PeopleService
import com.example.tmbdapp.detail_actor.domain.PeopleRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
internal interface DetailActorModule {
    @Binds
    fun bindDetailActorRepository(authRepositoryImpl: PeopleRepositoryImpl): PeopleRepository

}