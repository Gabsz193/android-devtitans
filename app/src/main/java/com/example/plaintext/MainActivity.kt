package com.example.plaintext

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.plaintext.ui.theme.PlainTextTheme
import kotlinx.serialization.Serializable

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PlainTextTheme {
                val navController = rememberNavController()
                Scaffold (
                    topBar = {
                        TopBar()
                    }
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = ScreenLogin
                    ) {
                        composable<ScreenLogin> {
                            ContentLogin(controller = navController, modifier = Modifier.padding(innerPadding))
                        }
                        composable<ScreenHello> {
                            val args = it.toRoute<ScreenHello>()
                            ContentHello(args, modifier = Modifier.padding(innerPadding))
                        }
                    }
                }
            }
        }
    }
}

@Serializable
object ScreenLogin

@Serializable
data class ScreenHello (
    val name: String?
)

@Composable
fun ContentHello(args: ScreenHello, modifier: Modifier = Modifier) {
    Column (modifier = modifier
        .fillMaxHeight()
        .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Hello ${args.name}")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBar() {
    var expanded by remember { mutableStateOf(false) }

    TopAppBar(
        title = { Text("PlainText") },
        actions = {
            IconButton(onClick = { expanded = true }) {
                Icon(Icons.Default.MoreVert, contentDescription = "Menu")
            }
        }
    )
}

@Composable
fun ContentLogin(modifier: Modifier = Modifier, controller: NavController) {
    Column {
        SliceBar(modifier)
        LoginFields(controller = controller)
    }
}

@Composable
fun LoginFields(modifier: Modifier = Modifier, controller: NavController) {
    var login by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }
    var rememberLogin by remember { mutableStateOf(false) }
    val context = LocalContext.current


    Column (
        modifier = modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Text("Digite suas credencias para continuar")
        Column (
            modifier = modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Row (
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Login:")
                OutlinedTextField(
                    value = login,
                    onValueChange = { login = it },

                )
            }
            Row (
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Senha:")
                OutlinedTextField(
                    value = senha,
                    onValueChange = { senha = it },
                    visualTransformation = PasswordVisualTransformation()
                )
            }
            Spacer (Modifier.width(10.dp))
            Row (
                modifier = modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = rememberLogin,
                    onCheckedChange = { rememberLogin = it }
                )
                Text("Salvar as informações de login")
            }

            Button (
                onClick = {
                    if(login == "" || senha == "") {
                        Toast.makeText(context, "Insira um login e senha", Toast.LENGTH_SHORT).show()
                    } else {
                        controller.navigate(ScreenHello(name = login))
                    }
                }
            ) {
                Text("Enviar")
            }
        }
    }


}

@Composable
fun SliceBar(modifier: Modifier = Modifier) {
    Row (
        modifier = modifier
            .fillMaxWidth()
            .background(Color(0xff69db7c)),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = "Logo"
        )
        Text(
            color = Color.White,
            text = "\"The most secure\npassword manager\"\nBob and Alice"
        )
    }
}