package com.example.tmdbapp.presentation.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import cafe.adriel.voyager.core.screen.Screen
import com.example.tmdbapp.R

class ProfileScreen : Screen {
    @Composable
    override fun Content() {
        val viewModel = hiltViewModel<ProfileViewModel>()
        val state by viewModel.getState().collectAsState()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = colorResource(R.color.primary)
                    )
                    .padding(16.dp)
            ) {
                Icon(
                    modifier = Modifier
                        .width(64.dp)
                        .height(32.dp)
                        .align(Alignment.Center),
                    painter = painterResource(R.drawable.ic_main_logo),
                    contentDescription = null
                )
            }
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    modifier = Modifier.padding(bottom = 16.dp),
                    text = stringResource(R.string.profile_login_title),
                    color = Color.Black,
                    style = TextStyle(
                        fontSize = TextUnit(24f, TextUnitType.Sp),
                        fontWeight = FontWeight(600)
                    )
                )
                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 4.dp)
                        .border(
                            2.dp,
                            color = colorResource(R.color.primary),
                            RoundedCornerShape(8.dp)
                        ),
                    value = state.login,
                    label = { Text(stringResource(R.string.profile_login_label)) },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        disabledContainerColor = Color.White,
                        focusedTextColor = Color.Black,
                        cursorColor = Color.Black,
                        focusedLabelColor = Color.Black,
                        unfocusedLabelColor = Color.Black,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    onValueChange = {
                        viewModel.updateLogin(it)
                    }
                )
                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                        .border(
                            2.dp,
                            color = colorResource(R.color.primary),
                            RoundedCornerShape(8.dp)
                        ),
                    value = state.password,
                    label = { Text(stringResource(R.string.profile_password_label)) },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        disabledContainerColor = Color.White,
                        focusedTextColor = Color.Black,
                        cursorColor = Color.Black,
                        focusedLabelColor = Color.Black,
                        unfocusedLabelColor = Color.Black,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    onValueChange = {
                        viewModel.updatePassword(it)
                    }
                )
                Text(
                    text = stringResource(R.string.profile_login_description),
                    color = Color.Black,
                    style = TextStyle(
                        fontSize = TextUnit(16f, TextUnitType.Sp),
                        fontWeight = FontWeight(400)
                    )
                )
                Spacer(modifier = Modifier.weight(weight = 1f))
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(R.color.primary),
                    ),
                    onClick = {
                        viewModel.onLoginClick()
                    }) {
                    Text(
                        modifier = Modifier.padding(vertical = 8.dp),
                        color = Color.White,
                        text = "Войти".uppercase(),
                        style = TextStyle(
                            fontSize = TextUnit(18f, TextUnitType.Sp),
                            fontWeight = FontWeight(800)
                        )
                    )
                }
            }
        }
    }
}