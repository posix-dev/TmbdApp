package com.example.tmdbapp.home.presentation

import android.widget.Toast
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import cafe.adriel.voyager.core.registry.ScreenRegistry
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.tmdbapp.common.nav.navigation.SharedScreen
import com.example.tmdbapp.common.ui.composable.CardMovieComponent
import com.example.tmdbapp.home.R
import com.example.tmdbapp.model.domain.MoviesCategory
import com.example.tmdbapp.model.domain.Results

class MainScreen : Screen {

    @Composable
    override fun Content() {
        val viewModel = hiltViewModel<MainViewModel>()
        val state by viewModel.getState().collectAsState()
        val context = LocalContext.current

        val navigator = LocalNavigator.currentOrThrow
        val goToDetail: (Int) -> Unit = {
            viewModel.onAction(
                MainViewModel.Action.OnClickedCard(it)
            )
        }
        val goToAllMovies: (String) -> Unit = {
            viewModel.onAction(
                MainViewModel.Action.OnAllWatchClicked(it)
            )
        }

        LaunchedEffect(key1 = context) {
            viewModel.getEvents().collect {
                when (it) {
                    is MainViewModel.Event.ShowError -> {
                        Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                    }

                    is MainViewModel.Event.GoToDetailScreen -> {
                        navigator.push(
                            ScreenRegistry.get(SharedScreen.DetailMovie(it.id))
                        )
                    }

                    is MainViewModel.Event.GoToAllMoviesScreen -> {
                        navigator.push(
                            ScreenRegistry.get(SharedScreen.AllMovies(it.category))
                        )
                    }
                }
            }
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            if (state.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
            ) {
                item {
                    MovieItemTitle(R.string.home_popular_movies_subtitle
                    ) {
                        goToAllMovies(MoviesCategory.POPULAR.category)
                    }
                    PopularList(state.popularItems, goToDetail)
                }
                item {
                    MovieItemTitle(R.string.home_now_playing_movies_subtitle) {
                        goToAllMovies(MoviesCategory.NOW_PLAYING.category)
                    }
                    PopularList(state.nowPlayingItems, goToDetail)
                }
                item {
                    MovieItemTitle(R.string.home_top_rated_movies_subtitle) {
                        goToAllMovies(MoviesCategory.HIGH_RATING.category)
                    }
                    PopularList(state.topRatedItems, goToDetail)
                }
                item {
                    MovieItemTitle(R.string.home_upcoming_movies_subtitle) {
                        goToAllMovies(MoviesCategory.UPCOMING.category)
                    }
                    PopularList(state.upcomingItems, goToDetail)
                }
            }
        }
    }

    @Composable
    fun MovieItemTitle(
        @StringRes title: Int,
        onClick: () -> Unit,
    ) {
        Row {
            Text(
                style = TextStyle(
                    fontSize = TextUnit(16f, TextUnitType.Sp),
                    fontWeight = FontWeight.W600
                ),
                text = stringResource(title),
                color = Color.Black
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                modifier = Modifier.clickable(
                    enabled = true,
                    onClick = onClick
                ),
                text = stringResource(R.string.home_view_all),
                style = TextStyle(
                    fontSize = TextUnit(16f, TextUnitType.Sp),
                    fontWeight = FontWeight.W600
                ),
                color = Color(0xFF999999)
            )
        }
    }

    @Composable
    fun PopularList(items: List<Results>, goToDetail: (Int) -> Unit) {
        LazyRow(modifier = Modifier.padding(vertical = 16.dp)) {
            items(items) {
                CardMovieComponent(
                    url = it.posterPath,
                    title = it.title,
                    releaseDate = it.releaseDate,
                    onClick = { goToDetail(it.id) }
                )
            }
        }
    }

}