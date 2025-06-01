package com.example.tmdbapp

import android.app.Application
import cafe.adriel.voyager.core.registry.ScreenRegistry
import cafe.adriel.voyager.core.registry.ScreenRegistry.register
import com.example.tmbdapp.all_movies.nav.featureAllMoviesScreenModule
import com.example.tmbdapp.detail_actor.nav.featureDetailActorScreenModule
import com.example.tmdbapp.common.nav.navigation.SharedScreen
import com.example.tmdbapp.detail_movie.nav.featurePostsScreenModule
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApp: Application() {
    override fun onCreate() {
        super.onCreate()

        ScreenRegistry {
            featurePostsScreenModule()
            featureDetailActorScreenModule()
            featureAllMoviesScreenModule()
        }
    }
}