package com.example.tmbdapp.detail_actor.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.core.screen.Screen
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.tmdbapp.common.ui.CustomColors

data class DetailActorScreen(val id: Int) : Screen {

    @Composable
    override fun Content() {
        val viewModel = hiltViewModel<DetailActorViewModel>()
        val state by viewModel.getState().collectAsStateWithLifecycle()

        LaunchedEffect(Unit) {
            viewModel.init(id)
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
                state.person?.let { person ->
                    val scrollState = rememberScrollState()
                    Column(
                        modifier = Modifier
                            .verticalScroll(scrollState)
                            .padding(bottom = 12.dp)
                    ) {
                        Box {
                            AsyncImage(
                                model = ImageRequest.Builder(LocalContext.current)
                                    .data("https://image.tmdb.org/t/p/original/${person.profilePath}")
                                    .crossfade(true)
                                    .build(),
                                contentDescription = null,
                                contentScale = ContentScale.FillWidth,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(224.dp)
                                    .clip(
                                        RoundedCornerShape(
                                            bottomStart = 16.dp,
                                            bottomEnd = 16.dp
                                        )
                                    )
                            )
                            Text(
                                modifier = Modifier
                                    .align(Alignment.BottomStart)
                                    .padding(start = 12.dp, bottom = 12.dp),
                                text = person.name,
                                color = CustomColors.White,
                                style = MaterialTheme.typography.headlineLarge
                            )
                        }

                        Column(modifier = Modifier.padding(start = 12.dp, top = 12.dp)) {
                            Text(
                                text = person.placeOfBirth,
                                color = Color.Black,
                                style = MaterialTheme.typography.titleLarge
                            )
                            Text(
                                text = person.popularity.toString(),
                                color = Color.Gray,
                                style = TextStyle(
                                    fontSize = TextUnit(16f, TextUnitType.Sp),
                                    fontWeight = FontWeight(400)
                                )
                            )

                            Row {
                                Text(
                                    text = person.birthday,
                                    color = CustomColors.Black,
                                    style = TextStyle(
                                        fontSize = TextUnit(16f, TextUnitType.Sp),
                                        fontWeight = FontWeight(400)
                                    )
                                )

                                if (person.deathday.isNotEmpty()) {
                                    Text(
                                        text = person.deathday,
                                        color = CustomColors.Black,
                                        style = TextStyle(
                                            fontSize = TextUnit(16f, TextUnitType.Sp),
                                            fontWeight = FontWeight(400)
                                        )
                                    )
                                }
                            }
                            Text(
                                modifier = Modifier.padding(top = 12.dp),
                                text = person.biography,
                                color = CustomColors.Black,
                                style = MaterialTheme.typography.bodyLarge
                            )
                        }
                    }
                }
            }
        }

    }
}