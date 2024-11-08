package com.example.plaintext.ui.screens.editList

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.plaintext.data.model.Password
import com.example.plaintext.ui.screens.Screen

data class EditListState(
    val nomeState: MutableState<String> = mutableStateOf<String>(""),
    val usuarioState: MutableState<String> = mutableStateOf<String>(""),
    val senhaState: MutableState<String> = mutableStateOf<String>(""),
    val notasState: MutableState<String> = mutableStateOf<String>(""),
)

fun isPasswordEmpty(password: Password): Boolean {
    return password.name.isEmpty() && password.login.isEmpty() && password.password.isEmpty() && password.notes?.isEmpty() ?: true
}

@Composable
fun EditList(
    args: Screen.EditList,
    navigateBack: () -> Unit,
    savePassword: (password: Password) -> Unit
) {
    val argsPassword = Password(
        args.id,
        args.name,
        args.login,
        args.password,
        args.notes
    )

    val editListState = EditListState()

    Scaffold { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFa4c639))
            ) {
                if(isPasswordEmpty(argsPassword)) {
                    Text("Adicionar nova senha")
                } else {
                    Text("Editar senha")
                }
            }
            EditInput(
                textInputLabel = "Nome",
                textInputState = editListState.nomeState
            )
            EditInput(
                textInputLabel = "Usuário",
                textInputState = editListState.usuarioState
            )
            EditInput(
                textInputLabel = "Senha",
                textInputState = editListState.senhaState
            )
            EditInput(
                textInputLabel = "Notas",
                textInputState = editListState.notasState
            )
            Button(
                onClick = {
                    savePassword(Password(
                        (Math.random() * 10000).toInt(),
                        editListState.nomeState.value,
                        editListState.usuarioState.value,
                        editListState.senhaState.value,
                        editListState.notasState.value
                    ))
                    navigateBack()
                }
            ) {
                Text("Salvar")
            }
        }
    }

}


@Composable
fun EditInput(
    textInputLabel: String,
    textInputState: MutableState<String> = mutableStateOf(""),
    textInputHeight: Int = 60
) {
    val padding: Int = 30

    var textState by rememberSaveable { textInputState }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(textInputHeight.dp)
            .padding(horizontal = padding.dp),
        horizontalArrangement = Arrangement.Center,
    ) {
        OutlinedTextField(
            value = textState,
            onValueChange = { textState = it },
            label = { Text(textInputLabel) },
            modifier = Modifier
                .height(textInputHeight.dp)
                .fillMaxWidth()
        )

    }
    Spacer(modifier = Modifier.height(10.dp))
}
