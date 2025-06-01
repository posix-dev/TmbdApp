package com.example.tmdbapp.di

import com.example.tmbdapp.detail_actor.data.api.PeopleService
import com.example.tmdbapp.BuildConfig.API_KEY
import com.example.tmdbapp.BuildConfig.API_URL
import com.example.tmdbapp.feature.profile.data.api.AuthService
import com.example.tmdbapp.network.api.MovieService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import tech.thdev.network.flowcalladapterfactory.FlowCallAdapterFactory

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private const val AUTH = "Authorization"

    @Singleton
    @Provides
    fun providesOkHttpClient(): OkHttpClient = OkHttpClient
        .Builder()
        .addInterceptor(HttpLoggingInterceptor()
            .apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
        .addInterceptor { chain ->
            val request = chain.request()
            chain.proceed(
                request.newBuilder().addHeader(
                    AUTH, "Bearer $API_KEY"
                )
                    .build()
            )
        }
        .build()

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(FlowCallAdapterFactory())
        .baseUrl(API_URL)
        .client(okHttpClient)
        .build()

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): MovieService =
        retrofit.create(MovieService::class.java)

    @Singleton
    @Provides
    fun provideAuthenticateService(retrofit: Retrofit): AuthService =
        retrofit.create(AuthService::class.java)

    @Singleton
    @Provides
    fun providePeopleService(retrofit: Retrofit): PeopleService =
        retrofit.create(PeopleService::class.java)

    @Singleton
    @Provides
    fun provideApiKey() = API_KEY

}