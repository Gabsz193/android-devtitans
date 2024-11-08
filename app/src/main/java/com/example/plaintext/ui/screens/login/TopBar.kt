package com.example.plaintext.ui.screens.login

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    navigateToSettings: () -> Unit,
) {
    TopAppBar(
        title = {
            Text("PlainText")
        },
        actions = {
            IconButton(onClick = navigateToSettings) { Icon(Icons.Default.MoreVert, contentDescription = "More options") }
        }
    )
}