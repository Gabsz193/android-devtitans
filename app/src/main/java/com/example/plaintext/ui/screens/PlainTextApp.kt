package com.example.plaintext.ui.screens

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.plaintext.data.model.Password
import com.example.plaintext.ui.screens.editList.EditList
import com.example.plaintext.ui.screens.list.ListView
import com.example.plaintext.ui.screens.login.Login_screen
import com.example.plaintext.ui.screens.preferences.SettingsScreen
import com.example.plaintext.ui.viewmodel.ListViewModel
import com.example.plaintext.ui.viewmodel.PreferencesViewModel
import com.example.plaintext.utils.parcelableType
import kotlin.reflect.typeOf

@Composable
fun PlainTextApp(
    appState: JetcasterAppState = rememberJetcasterAppState()
) {
    val preferencesViewModel : PreferencesViewModel = hiltViewModel()
    val listViewModel : ListViewModel = hiltViewModel()

    NavHost(
        navController = appState.navController,
        startDestination = Screen.Login,
    )
    {
        composable<Screen.Login>{
            Login_screen(
                navigateToSettings = { appState.navigateToSettings() },
                navigateToList = { appState.navigateToList() },
                preferencesViewModel = preferencesViewModel
            )
        }
        composable<Screen.Preferences> {
            SettingsScreen(navController = appState.navController, preferencesViewModel)
        }
        composable<Screen.EditList> {
            val args = it.toRoute<Screen.EditList>()
            EditList(
                args,
                navigateBack = { appState.navigateToList() },
                savePassword = { password -> listViewModel.savePassword(password) }
            )
        }
        composable<Screen.List> {
            ListView(appState = appState)
        }
    }
}