package com.example.tmbdapp.all_movies.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.core.registry.ScreenRegistry
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.tmdbapp.common.nav.navigation.SharedScreen
import com.example.tmdbapp.common.ui.composable.CardMovieComponent

data class AllMoviesScreen(
    val category: String,
) : Screen {

    @Composable
    override fun Content() {
        val viewModel = hiltViewModel<AllMoviesViewModel>()
        val state by viewModel.getState().collectAsStateWithLifecycle()
        val navigator = LocalNavigator.currentOrThrow
        val goToDetail: (Int) -> Unit = {
            viewModel.onAction(
                AllMoviesViewModel.Action.OnCardClicked(it)
            )
        }

        LaunchedEffect(Unit) {
            viewModel.init(category)
            viewModel.getEvents().collect {
                when (it) {
                    is AllMoviesViewModel.Event.GoToDetailScreen -> {
                        navigator.push(
                            ScreenRegistry.get(SharedScreen.DetailMovie(it.id))
                        )
                    }
                }
            }
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Companion.White)
        ) {
            if (state.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            } else {
                if (state.movies == null) return

                LazyVerticalStaggeredGrid(
                    columns = StaggeredGridCells.Fixed(2),
                    verticalItemSpacing = 4.dp,
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    content = {
                        state.movies?.run {
                            items(results) {
                                CardMovieComponent(
                                    url = it.posterPath,
                                    title = it.title,
                                    releaseDate = it.releaseDate,
                                    onClick = { goToDetail(it.id) }
                                )
                            }
                        }
                    },
                    modifier = Modifier.fillMaxSize().padding(top = 16.dp, bottom = 2.dp)
                )
            }
        }
    }

}