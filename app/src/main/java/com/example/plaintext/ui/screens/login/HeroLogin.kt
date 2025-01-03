package com.example.aplicacaoteste.ui.screens.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.plaintext.R

@Composable
fun HeroLogin() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFa4c639)),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = "Logo"
        )
        Text(
            text = "\"The most\nsecure password\nmanager\"\nBob and Alice",
            color = Color.White,
            lineHeight = 20.sp,
            fontSize = 15.sp
        )
    }
}

@Preview
@Composable
fun PreviewHeroLogin() {
    HeroLogin()
}