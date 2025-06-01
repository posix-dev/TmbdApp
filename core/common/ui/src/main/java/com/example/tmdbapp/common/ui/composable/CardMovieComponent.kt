package com.example.tmdbapp.common.ui.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import coil3.compose.SubcomposeAsyncImage
import coil3.compose.SubcomposeAsyncImageContent
import com.example.tmdbapp.common.ui.R

@Composable
fun CardMovieComponent(
    modifier: Modifier = Modifier,
    loadingImgModifier: Modifier = Modifier,
    title: String,
    url: String? = null,
    releaseDate: String? = null,
    onClick: () -> Unit = {}
) {
    Card(
        modifier = Modifier
            .padding(start = 2.dp)
            .width(150.dp)
            .height(230.dp)
            .padding(end = 8.dp)
            .clickable {
                onClick()
            },
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
    ) {
        SubcomposeAsyncImage(
            modifier = Modifier.height(150.dp),
            model = "https://image.tmdb.org/t/p/original/$url",
            contentDescription = null,
            loading = {
                CircularProgressIndicator(
                    modifier = Modifier
                        .fillMaxSize()
                        .align(Alignment.Center)
                )
            },
            success = {
                SubcomposeAsyncImageContent()
            },
            error = {
                Image(
                    modifier = Modifier.fillMaxSize(),
                    painter = painterResource(R.drawable.ic_thumbnail),
                    contentDescription = null
                )
            },
            contentScale = ContentScale.FillWidth,
        )
        Column(modifier = Modifier.padding(start = 12.dp, top = 12.dp)) {
            releaseDate?.let {
                Text(
                    style = TextStyle(
                        fontSize = TextUnit(14f, TextUnitType.Sp),
                        fontWeight = FontWeight.W800
                    ),
                    text = releaseDate,
                    color = Color.Black,

                )
            }
            Text(
                style = TextStyle(
                    fontSize = TextUnit(14f, TextUnitType.Sp),
                ),
                text = title,
                color = Color.Black
            )
        }
    }
}