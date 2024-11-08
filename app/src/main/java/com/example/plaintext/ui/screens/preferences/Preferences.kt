package com.example.plaintext.ui.screens.preferences


import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.plaintext.ui.screens.login.TopBarComponent
import com.example.plaintext.ui.screens.util.PreferenceInput
import com.example.plaintext.ui.screens.util.PreferenceItem
import com.example.plaintext.ui.viewmodel.PreferencesViewModel

@Composable
fun SettingsScreen(navController: NavHostController?,
                   viewModel: PreferencesViewModel = hiltViewModel()
){
    Scaffold(
        topBar = {
            TopBarComponent()
        }
    ){ padding ->
        SettingsContent(modifier = Modifier.padding(padding), viewModel, navController)
    }
}

@Composable
fun SettingsContent(modifier: Modifier = Modifier, viewModel: PreferencesViewModel, navController: NavHostController?) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())){

        PreferenceInput(
            title = "Setar Login",
            label = "Login",
            fieldValue = "",
            summary = "Login para entrar no sistema"

        ){
            viewModel.updateLogin(it)
        }

        PreferenceInput(
            title = "Setar Senha",
            label = "Label",
            fieldValue = "",
            summary = "Senha para entrar no sistema"
        ){
            viewModel.updatePassword(it)
        }

        PreferenceItem(
            title = "Preencher Login",
            summary = "Preencher login na tela inicial",
            onClick = { viewModel.updatePreencher() },
            control = {
                Switch(
                    checked = viewModel.preferencesState.preencher,
                    onCheckedChange = { viewModel.updatePreencher() }
                )
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SettingsScreenPreview() {
    SettingsScreen(null)
}