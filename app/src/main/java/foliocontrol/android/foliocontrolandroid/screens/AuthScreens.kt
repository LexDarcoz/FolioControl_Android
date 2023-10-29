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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import foliocontrol.android.foliocontrolandroid.context.AuthViewModel
import foliocontrol.android.foliocontrolandroid.context.LoginUiState

@Composable
fun AuthScreen(authViewModel: AuthViewModel) {
// Main auth
    when (authViewModel.loginUiState) {
        is LoginUiState.LoggedOut -> {
            LoginScreen(errorName = (authViewModel.loginUiState as LoginUiState.LoggedOut).message, authViewModel)
        }

        is LoginUiState.Success -> {
            HomeScreen(authViewModel)
        }

        is LoginUiState.Loading -> {
            LoadingScreen()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    errorName: String,
    authViewModel: AuthViewModel
) {
    var state = authViewModel.loginState.value

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
                    authViewModel.updateLoginState(email = it)
                }, // Update the username, not password
                label = { Text("Enter email") } // Correct the label text

            )
            TextField(
                value = state.password,
                onValueChange = { authViewModel.updateLoginState(password = it) },
                label = { Text("Enter password") },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )
            Text(text = errorName)
            val context = LocalContext.current
            Button( // Change the text
                onClick = {
                    authViewModel.login()
                    Toast.makeText(context, "Login clicked", Toast.LENGTH_SHORT).show()
                }
            ) {
                Text("Login")
            }
        }
    }
}

@Composable
fun LoadingScreen() {
    // give me a beautiful loading screeen
    Box(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(16.dp)
        ) {
            Text("Loading...")
        }
    }
}
