package com.example.tmbdapp.detail_actor.nav

import cafe.adriel.voyager.core.registry.screenModule
import com.example.tmbdapp.detail_actor.presentation.DetailActorScreen
import com.example.tmdbapp.common.nav.navigation.SharedScreen

val featureDetailActorScreenModule = screenModule {
    register<SharedScreen.DetailActor> { provider ->
        DetailActorScreen(provider.id)
    }
}