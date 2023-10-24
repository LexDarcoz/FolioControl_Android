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
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import foliocontrol.android.foliocontrolandroid.context.AuthViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(viewModel: AuthViewModel) {
    var state by viewModel.loginState

    Box(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(16.dp)
        ) {
            TextField(
                value = state.email,
                onValueChange = {
                    viewModel.updateLoginState(email = it)
                }, // Update the username, not password
                label = { Text("Enter email") } // Correct the label text

            )
            TextField(
                value = state.password,
                onValueChange = { viewModel.updateLoginState(password = it) },
                label = { Text("Enter password") },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )
            val context = LocalContext.current
            Button( // Change the text
                onClick = {
                    viewModel.login()

                    Toast.makeText(context, "Login clicked", Toast.LENGTH_SHORT).show()
                }
            ) {
                Text("Login")
            }
            TextButton(onClick = { viewModel.navigateTo("SignUp") }) {
                Text(text = "No Account? Register here!")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(viewModel: AuthViewModel) {
    val state by viewModel.signUpState
    Box(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(16.dp)
        ) {
            TextField(
                value = state.username,
                onValueChange = {
                    viewModel.updateSignUpState(username = it)
                }, // Update the username, not password
                label = { Text("Enter username") } // Correct the label text

            )
            TextField(
                value = state.email,
                onValueChange = {
                    viewModel.updateSignUpState(email = it)
                }, // Update the username, not password
                label = { Text("Enter email") } // Correct the label text

            )
            TextField(
                value = state.password,
                onValueChange = { viewModel.updateSignUpState(password = it) },
                label = { Text("Enter password") },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )
            val context = LocalContext.current
            Button( // Change the text
                onClick = {
                    Toast.makeText(context, "Make account clicked", Toast.LENGTH_SHORT).show()
                }
            ) {
                Text("Make account")
            }
            TextButton(onClick = { viewModel.showLogin() }) {
                Text(text = "Already have an account? Login here!")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignOutScreen() {
    var password by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }

    Box(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(16.dp)
        ) {
            TextField(
                value = email,
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
            Button( // Change the text
                onClick = {
                    Toast.makeText(context, "Login clicked", Toast.LENGTH_SHORT).show()
                }
            ) {
                Text("Login")
            }
        }
    }
}
