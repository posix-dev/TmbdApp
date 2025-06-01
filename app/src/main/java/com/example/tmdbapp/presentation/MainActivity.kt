package com.example.tmdbapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import com.example.tmdbapp.common.nav.navigation.TabNavigationItem
import com.example.tmdbapp.common.ui.TmdbAppTheme
import com.example.tmdbapp.feature.profile.ProfileTab
import com.example.tmdbapp.home.presentation.MainTab
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TmdbAppTheme {
                MainRoute()
            }
        }
    }
}

@Composable
fun MainRoute() {
    TabNavigator(MainTab) {
        Scaffold(
            content = { innerPadding ->
                Box(modifier = Modifier.padding(innerPadding)) {
                    CurrentTab()
                }
            },
            bottomBar = {
                NavigationBar {
                    TabNavigationItem(MainTab)
                    TabNavigationItem(ProfileTab)
                }
            }
        )
    }
}