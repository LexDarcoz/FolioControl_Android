package foliocontrol.android.foliocontrolandroid.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import foliocontrol.android.foliocontrolandroid.api.UserLoginRequest
import foliocontrol.android.foliocontrolandroid.api.UserLoginRequestData
import foliocontrol.android.foliocontrolandroid.context.LocalTokenState
import foliocontrol.android.foliocontrolandroid.context.User
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavController) {
    var password by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    val scope = rememberCoroutineScope()
    val userState = remember {
        mutableStateOf(
            User()
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp), contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(16.dp)
        ) {
            TextField(value = email,
                onValueChange = { email = it }, // Update the username, not password
                label = { Text("Enter email") } // Correct the label text

            )
            TextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Enter password") },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )
            val context = LocalContext.current
            Button(onClick = {

                if (email.isNotEmpty() && password.isNotEmpty()) {
                    //await the result

                    scope.launch {

                        UserLoginRequest(email, password, userState)

                        if (userState.value.token.isNotEmpty()) {
                            navController.navigate("Home")
                            Toast.makeText(context, "Welcome ${email}", Toast.LENGTH_SHORT).show()
                        } else {
//                    throw a toast
                            Toast.makeText(
                                context,
                                "Login failed, check your credentials",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }

                } else {
                    Toast.makeText(context, "Please enter email and password", Toast.LENGTH_SHORT)
                        .show()
                }
            }) {
                Text(text = "Login")
            }
        }
    }
}
