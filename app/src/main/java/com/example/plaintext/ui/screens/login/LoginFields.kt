package com.example.aplicacaoteste.ui.screens.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.plaintext.ui.viewmodel.LoginViewModel

@Composable
fun LoginFields(
    loginViewModel: LoginViewModel
) {
//    var login by remember { mutableStateOf("") }
//    var senha by remember { mutableStateOf("") }
//    var rememberLogin by remember { mutableStateOf(false) }
    val context = LocalContext.current

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
                onChange = { loginViewModel.setLogin(context, it) }
            ))
            TextField(FieldProperties(
                title = "Senha",
                value = loginViewModel.loginViewState.senha,
                onChange = { loginViewModel.setSenha(it) }
            ))
        }
        Spacer(modifier = Modifier.height(35.dp))
        CheckBoxField(CheckBoxProperties(
            checked = loginViewModel.loginViewState.remeberLogin,
            onChange = { loginViewModel.setRemeberLogin() },
            description = "Salvar as informações de login"
        ))
    }
}

data class FieldProperties (
    val title: String,
    var value : String,
    val onChange: (it : String) -> Unit
)

@Composable
fun TextField(properties: FieldProperties) {
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
        OutlinedTextField(
            value = properties.value,
            onValueChange = properties.onChange,
            modifier = Modifier
                .height(40.dp)
        )
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