package com.example.tmdbapp.detail_movie.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import cafe.adriel.voyager.core.registry.ScreenRegistry
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.tmdbapp.common.nav.navigation.SharedScreen
import com.example.tmdbapp.common.ui.composable.CardMovieComponent
import com.example.tmdbapp.detail_movie.R
import com.example.tmdbapp.detail_movie.domain.Genres
import com.example.tmdbapp.extension.minuteToTime

data class DetailMovieScreen(val id: Int) : Screen {

    @Composable
    override fun Content() {
        val viewModel = hiltViewModel<DetailMovieViewModel>()
        val state by viewModel.getState().collectAsState()
        val navigator = LocalNavigator.currentOrThrow

        LaunchedEffect(Unit) {
            viewModel.init(id)
            viewModel.getEvents().collect {
                when (it) {
                    is DetailMovieViewModel.Event.GoToDetailScreen -> {
                        navigator.push(
                            ScreenRegistry.get(SharedScreen.DetailActor(it.id))
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
                val scrollState = rememberScrollState()

                Column(modifier = Modifier.verticalScroll(scrollState)) {
                    PosterComponent(
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        state.detailMovie?.backdropPath,
                        state.detailMovie?.posterPath
                    )
                    TitleComponent(
                        Modifier.align(Alignment.CenterHorizontally),
                        state.detailMovie?.title,
                        state.detailMovie?.titleYear,
                        state.detailMovie?.originalLanguage,
                        state.detailMovie?.runtime,
                        state.detailMovie?.genres.orEmpty(),
                        state.detailMovie?.voteAverage
                    )

                    OverviewComponent(
                        Modifier.padding(start = 12.dp, top = 12.dp),
                        state.detailMovie?.overview
                    )

                    state.movieCredits?.let { credits ->
                        Text(
                            modifier = Modifier.padding(start = 12.dp, top = 12.dp, bottom = 12.dp),
                            style = MaterialTheme.typography.titleLarge,
                            color = Color.Black,
                            text = stringResource(R.string.detail_movie_actor_cast)
                        )
                        LazyRow(modifier = Modifier.padding(start = 12.dp, bottom = 12.dp)) {
                            items(credits.cast) {
                                CardMovieComponent(
                                    url = it.profilePath,
                                    title = it.name,
                                    onClick = {
                                        viewModel.onAction(
                                            DetailMovieViewModel.Action.OnCardClicked(it.id)
                                        )
                                    }
                                )
                            }
                        }
                    }
                }
            }

        }
    }

    @Composable
    fun OverviewComponent(
        modifier: Modifier = Modifier,
        overview: String?,
    ) {
        Column(modifier = modifier) {
            Text(
                style = MaterialTheme.typography.titleLarge,
                color = Color.Black,
                text = stringResource(R.string.detail_movie_description_movie)
            )
            Text(
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Black,
                text = overview.orEmpty()
            )
        }
    }

    @Composable
    fun TitleComponent(
        modifier: Modifier = Modifier,
        title: String?,
        titleYear: String?,
        originalLanguage: String?,
        runtime: Int?,
        genres: List<Genres>,
        voteAverage: Double?,
    ) {
        Row(modifier = modifier) {
            Text(
                modifier = Modifier.align(Alignment.CenterVertically),
                style = MaterialTheme.typography.titleLarge,
                color = Color.Black,
                text = title.orEmpty()
            )
            Text(
                modifier = Modifier.align(Alignment.CenterVertically),
                style = MaterialTheme.typography.titleLarge,
                color = Color.Gray,
                text = titleYear.orEmpty()
            )
        }
        Row(modifier = modifier) {
            originalLanguage?.uppercase().orEmpty().let {
                Text(
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.Black,
                    text = "$it "
                )
            }
            Text(
                style = MaterialTheme.typography.titleMedium,
                color = Color.Black,
                text = runtime?.minuteToTime() ?: ""
            )
        }

        val genreNames = buildString {
            genres.forEachIndexed { index, genre ->
                append(
                    if (index == genres.lastIndex) {
                        genre.name
                    } else {
                        "${genre.name}, "
                    }
                )
            }
        }

        Text(
            modifier = modifier.padding(horizontal = 12.dp),
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray,
            textAlign = TextAlign.Center,
            text = genreNames,
        )

        Row(modifier = modifier) {
            CircularProgress(
                Modifier.padding(start = 12.dp, top = 8.dp),
                voteAverage?.toFloat()?.div(10) ?: 0f
            )
            Row(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(start = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier
                        .padding(end = 8.dp),
                    text = stringResource(R.string.detail_movie_rating),
                    color = Color.Black,
                    style = MaterialTheme.typography.bodyMedium
                )
                VerticalDivider(
                    modifier = Modifier
                        .height(20.dp)
                        .width(3.dp)
                        .background(Color.LightGray)
                )
                Row(modifier = Modifier.clickable {
                    // todo go to the Youtube
                }) {
                    Icon(
                        modifier = Modifier.size(24.dp),
                        imageVector = Icons.Default.PlayArrow,
                        contentDescription = null,
                        tint = Color.Black,
                    )
                    Text(
                        text = stringResource(R.string.detail_movie_trailer),
                        color = Color.Black,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }

    @Composable
    fun PosterComponent(
        modifier: Modifier = Modifier,
        backdropPath: String?,
        posterPath: String?,
    ) {
        Box(modifier = modifier) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data("https://image.tmdb.org/t/p/original/$backdropPath")
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(224.dp)
                    .clip(RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp))
            )
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data("https://image.tmdb.org/t/p/original/$posterPath")
                    .crossfade(true).build(),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .padding(start = 12.dp)
                    .width(120.dp)
                    .height(160.dp)
                    .align(Alignment.CenterStart)
                    .clip(shape = RoundedCornerShape(16.dp))
            )
        }
    }

    @Composable
    fun CircularProgress(
        modifier: Modifier = Modifier,
        progressValue: Float,
    ) {
        Box(
            modifier = modifier
                .width(50.dp)
                .height(50.dp)
                .clip(shape = CircleShape)
                .background(color = Color.Black)
        ) {
            CircularProgressIndicator(
                progress = { progressValue },
                color = Color.Yellow,
                trackColor = Color.Black,
                modifier = Modifier
                    .align(Alignment.Center)
                    .height(38.dp)
                    .width(38.dp),

                )
            val percentage = (progressValue * 100).toInt()
            Text(
                text = buildAnnotatedString {
                    append("$percentage");
                    withStyle(style = SpanStyle(fontSize = 10.sp)) {
                        append(
                            "%"
                        )
                    }
                },
                color = Color.White,
                style = MaterialTheme.typography.bodySmall,
                fontSize = 12.sp,
                maxLines = 1,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}