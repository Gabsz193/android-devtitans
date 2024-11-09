package com.example.plaintext.ui.screens

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.plaintext.data.model.Password
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable
sealed class Screen() {

    @Serializable
    object Login;

    @Serializable
    object Preferences;

    @Serializable
    object List;

    @Serializable
    data class EditList(
        val id : Int,
        val name: String,
        val login: String,
        val password: String,
        val notes: String?
    );
}

@Composable
fun rememberJetcasterAppState(
    navController: NavHostController = rememberNavController(),
    context: Context = LocalContext.current
) = remember(navController, context) {
    JetcasterAppState(navController, context)
}


class JetcasterAppState(
    val navController: NavHostController,
    private val context: Context
) {

    fun checkRoute(route: String): Boolean {
        val currentRoute = navController.currentBackStackEntry?.destination?.route.toString()

        return currentRoute != route
    }

    fun navigateToSettings() {
        navController.navigate(Screen.Preferences)
    }

    fun navigateToList() {
        navController.navigate(Screen.List)
    }

    fun navigateToEditList(password: Password) {
        navController.navigate(Screen.EditList(
            password.id,
            password.name,
            password.login,
            password.password,
            password.notes
        ))
    }

    fun navigateToLogin(){
        navController.navigate(Screen.Login)
    }

}

/**
 * If the lifecycle is not resumed it means this NavBackStackEntry already processed a nav event.
 *
 * This is used to de-duplicate navigation events.
 */
private fun NavBackStackEntry.lifecycleIsResumed() =
    this.lifecycle.currentState == Lifecycle.State.RESUMED
