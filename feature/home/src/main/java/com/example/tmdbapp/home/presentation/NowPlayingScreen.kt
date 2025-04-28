package com.example.tmdbapp.home.presentation

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import cafe.adriel.voyager.core.screen.Screen
import coil3.compose.AsyncImage
import  com.example.tmdbapp.home.R
import com.example.tmdbapp.model.domain.Results

class NowPlayingScreen : Screen {

    @Composable
    override fun Content() {
        val viewModel = hiltViewModel<NowPlayingViewModel>()
        val state by viewModel.getState().collectAsState()
        val context = LocalContext.current

        LaunchedEffect(key1 = context) {
            viewModel.getEvents().collect {
                when (it) {
                    is NowPlayingViewModel.Event.ShowError -> {
                        Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
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
                    Row {
                        Text(
                            style = TextStyle(
                                fontSize = TextUnit(16f, TextUnitType.Sp),
                                fontWeight = FontWeight.W600
                            ),
                            text = stringResource(R.string.home_popular_movies_subtitle),
                            color = Color.Black
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Text(
                            modifier = Modifier.clickable(
                                enabled = true,
                                onClick = {
                                    // todo добей переход на экран Все популярное
                                }
                            ),
                            text = stringResource(R.string.home_view_all),
                            style = TextStyle(
                                fontSize = TextUnit(16f, TextUnitType.Sp),
                                fontWeight = FontWeight.W600
                            ),
                            color = Color(0xFF999999)
                        )
                    }
                    PopularList(state.popularItems)
                }
                item {
                    Row {
                        Text(
                            style = TextStyle(
                                fontSize = TextUnit(16f, TextUnitType.Sp),
                                fontWeight = FontWeight.W600
                            ),
                            text = stringResource(R.string.home_now_playing_movies_subtitle),
                            color = Color.Black
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Text(
                            modifier = Modifier.clickable(
                                enabled = true,
                                onClick = {
                                    // todo добей переход на экран Все популярное
                                }
                            ),
                            text = stringResource(R.string.home_view_all),
                            style = TextStyle(
                                fontSize = TextUnit(16f, TextUnitType.Sp),
                                fontWeight = FontWeight.W600
                            ),
                            color = Color(0xFF999999)
                        )
                    }
                    PopularList(state.nowPlayingItems)
                }
            }
        }
    }

    @Composable
    fun PopularList(items: List<Results>) {
        LazyRow(modifier = Modifier.padding(vertical = 16.dp)) {
            items(items) {
                    Card(
                        modifier = Modifier
                            .width(150.dp)
                            .height(230.dp)
                            .padding(end = 8.dp)
                            .shadow(elevation = 8.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.White
                        ),
                    ) {
                        AsyncImage(
                            modifier = Modifier.height(150.dp),
                            model = "https://image.tmdb.org/t/p/original/" + it.posterPath,
                            contentDescription = null,
                            contentScale = ContentScale.FillWidth,
                        )
                        Text(
                            modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp),
                            style = TextStyle(
                                fontSize = TextUnit(14f, TextUnitType.Sp),
                            ),
                            text = it.releaseDate,
                            color = Color.Black
                        )
                        Text(
                            modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
                            style = TextStyle(
                                fontSize = TextUnit(14f, TextUnitType.Sp),
                            ),
                            text = it.title,
                            color = Color.Black,
                            maxLines = 2
                        )
                    }
            }
        }
    }

}