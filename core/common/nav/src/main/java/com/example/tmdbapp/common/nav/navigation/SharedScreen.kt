package com.example.tmdbapp.common.nav.navigation

import cafe.adriel.voyager.core.registry.ScreenProvider

sealed class SharedScreen : ScreenProvider {
    data object Profile : SharedScreen()
    data object NowPlaying : SharedScreen()
}