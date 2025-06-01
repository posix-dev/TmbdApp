package com.example.tmdbapp.common.nav.navigation

import cafe.adriel.voyager.core.registry.ScreenProvider

sealed class SharedScreen : ScreenProvider {
    data class DetailMovie(val id: Int) : SharedScreen()
    data class DetailActor(val id: Int) : SharedScreen()

    data class AllMovies(val category: String) : SharedScreen()
}