package com.example.plaintext.ui.screens.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.sharp.Lock
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.plaintext.ui.viewmodel.LoginViewModel
import com.example.plaintext.ui.viewmodel.PreferencesViewModel

@Composable
fun LoginFields(
    loginViewModel: LoginViewModel,
    preferencesViewModel: PreferencesViewModel
) {
    LaunchedEffect(
        preferencesViewModel.preferencesState.login,
        preferencesViewModel.preferencesState.preencher
    ) {
        if (preferencesViewModel.preferencesState.preencher) {
            loginViewModel.setLogin(preferencesViewModel.preferencesState.login)
            loginViewModel.setSenha("")
        } else {
            loginViewModel.setLogin("")
            loginViewModel.setSenha("")
        }
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "Digite suas credenciais para continuar",
        )
        Spacer(modifier = Modifier.height(15.dp))
        Column(
            modifier = Modifier.padding(PaddingValues(horizontal = 20.dp)),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            TextField(FieldProperties(
                title = "Login",
                value = loginViewModel.loginViewState.login,
                onChange = { loginViewModel.setLogin(it) }
            ))
            TextField(FieldProperties(
                title = "Senha",
                value = loginViewModel.loginViewState.senha,
                onChange = { loginViewModel.setSenha(it) },
            ),isPassword = true)
        }
        Spacer(modifier = Modifier.height(35.dp))
        CheckBoxField(
            CheckBoxProperties(
            checked = loginViewModel.loginViewState.remeberLogin,
            onChange = { loginViewModel.setRemeberLogin() },
            description = "Salvar as informações de login"
        )
        )
    }
}

data class FieldProperties (
    val title: String,
    var value : String,
    val onChange: (it : String) -> Unit
)

@Composable
fun TextField(properties: FieldProperties, isPassword : Boolean = false) {
    var passwordVisible by rememberSaveable { mutableStateOf(false) }

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Text(
            text = "${properties.title}:",
            textAlign = TextAlign.End,
            modifier = Modifier.weight(1f)
        )
        if(isPassword) {
            OutlinedTextField(
                value = properties.value,
                onValueChange = properties.onChange,
                singleLine = true,
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                trailingIcon = {
                    val image = if (passwordVisible)
                        Icons.Outlined.Lock
                    else Icons.Filled.Lock

                    IconButton(onClick = {passwordVisible = !passwordVisible}){
                        Icon(imageVector  = image, contentDescription = "Mostrar senha")
                    }
                }
            )
        } else {
            OutlinedTextField(
                value = properties.value,
                onValueChange = properties.onChange,
                modifier = Modifier
                    .height(50.dp),
            )
        }
    }
}

data class CheckBoxProperties(
    val description : String,
    var checked : Boolean,
    val onChange: (it: Boolean) -> Unit
)

@Composable
fun CheckBoxField(properties: CheckBoxProperties) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = properties.checked,
            onCheckedChange = properties.onChange,
        )
        Text(
            text = properties.description,
        )
    }
}