package com.example.plaintext.ui.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

data class LoginViewState(
    var login: String = "",
    var senha: String = "",
    var remeberLogin: Boolean = false
)

@HiltViewModel
open class LoginViewModel @Inject constructor() : ViewModel() {
    var loginViewState by mutableStateOf(LoginViewState())
        private set

    fun setLogin(context : Context, value : String) {
        Toast.makeText(
            context,
            "Login: ${loginViewState.login}\n" +
            "Senha: ${loginViewState.senha}\n" +
            "RemeberLogin: ${loginViewState.remeberLogin}", Toast.LENGTH_LONG).show()
        loginViewState = loginViewState.copy(login = value)
    }

    fun setSenha(value : String) {
        loginViewState = loginViewState.copy(senha = value)
    }

    fun setRemeberLogin() {
        loginViewState = loginViewState.copy(remeberLogin = !loginViewState.remeberLogin)
    }

}