package com.example.tmdbapp.detail_movie.nav

import cafe.adriel.voyager.core.registry.screenModule
import com.example.tmdbapp.common.nav.navigation.SharedScreen
import com.example.tmdbapp.detail_movie.presentation.DetailMovieScreen

val featurePostsScreenModule = screenModule {
    register<SharedScreen.DetailMovie> { provider ->
        DetailMovieScreen(provider.id)
    }
}