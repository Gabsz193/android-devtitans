package com.example.plaintext.ui.screens.editList

import android.widget.Toast
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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

    var id by remember { mutableIntStateOf((Math.random() * 100000).toInt()) }

    LaunchedEffect(null) {
        if(args.name.isNotEmpty()) {
            id = args.id
        }
        editListState.nomeState.value = args.name
        editListState.usuarioState.value = args.login
        editListState.senhaState.value = args.password
        editListState.notasState.value = args.notes!!
    }

    val context = LocalContext.current


    Scaffold { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(Color(0xFFa4c639)),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if(isPasswordEmpty(argsPassword)) {
                    Text(
                        textAlign = TextAlign.Center,
                        fontSize = 28.sp,
                        color = Color.White,
                        text = "Adicionar nova senha"
                    )
                } else {
                    Text(
                        textAlign = TextAlign.Center,
                        fontSize = 28.sp,
                        color = Color.White,
                        text = "Editar senha"
                    )
                }
            }
            Spacer(modifier = Modifier.height(30.dp))
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
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
                    textInputState = editListState.notasState,
                    textInputHeight = 180
                )
            }
            Button(
                onClick = {

                    if(
                        editListState.nomeState.value.isEmpty() or
                        editListState.usuarioState.value.isEmpty() or
                        editListState.senhaState.value.isEmpty()
                    ) {

                        Toast.makeText(
                            context,
                            "Por favor, insira todos os valores obrigatórios",
                            Toast.LENGTH_LONG
                        ).show()
                    } else {
                        savePassword(Password(
                            id,
                            editListState.nomeState.value,
                            editListState.usuarioState.value,
                            editListState.senhaState.value,
                            editListState.notasState.value
                        ))
                        navigateBack()
                    }
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
