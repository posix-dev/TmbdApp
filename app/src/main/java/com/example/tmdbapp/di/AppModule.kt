package com.example.tmdbapp.di

import com.example.tmdbapp.data.api.NowPlayingService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private const val BASE_URL = "https://api.themoviedb.org/3/"
    private const val API_KEY =
        "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI5YzAxNjE5OWY2N2JhODg0NTY5NWRjMTZkNWM3ZWZiMCIsIm5iZiI6MTU4Nzk3OTE3OC4zMjA5OTk5LCJzdWIiOiI1ZWE2YTNhYTljMjRmYzAwMWZkNWI1OGUiLCJzY29wZXMiOlsiYXBpX3JlYWQiXSwidmVyc2lvbiI6MX0.AW_65BZt3NtWm8AVDJSWF4MfvjLkPeHqcUoHrubz-kw"

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
                    "Authorization", "Bearer $API_KEY"
                )
                    .build()
            )
        }
        .build()

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): NowPlayingService =
        retrofit.create(NowPlayingService::class.java)

}