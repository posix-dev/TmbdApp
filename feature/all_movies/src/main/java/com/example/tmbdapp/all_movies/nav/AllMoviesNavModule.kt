package com.example.tmbdapp.all_movies.nav

import cafe.adriel.voyager.core.registry.screenModule
import com.example.tmbdapp.all_movies.presentation.AllMoviesScreen
import com.example.tmdbapp.common.nav.navigation.SharedScreen

val featureAllMoviesScreenModule = screenModule {
    register<SharedScreen.AllMovies> { provider ->
        AllMoviesScreen(provider.category)
    }
}