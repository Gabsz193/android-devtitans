package com.example.plaintext.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
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

    fun setLogin(value : String) {
        loginViewState = loginViewState.copy(login = value)
    }

    fun setSenha(value : String) {
        loginViewState = loginViewState.copy(senha = value)
    }

    fun setRemeberLogin() {
        loginViewState = loginViewState.copy(remeberLogin = !loginViewState.remeberLogin)
    }

}