package foliocontrol.android.foliocontrolandroid.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import foliocontrol.android.foliocontrolandroid.ui.viewModels.AuthViewModel
import foliocontrol.android.foliocontrolandroid.ui.viewModels.PropertyViewModel
import foliocontrol.android.foliocontrolandroid.ui.viewModels.common.LoadingScreen
import foliocontrol.android.foliocontrolandroid.ui.viewModels.common.UiState

@Composable
fun AuthScreen(
    authViewModel: AuthViewModel,
    propertyViewModel: PropertyViewModel,
    navigateTo: (Any?) -> Unit = {}
) {
// Main auth
// For every screen a view-model is needed
    when (authViewModel.loginUiState) {
        is UiState.LoggedOut -> {
            LoginScreen(
                errorName = (authViewModel.loginUiState as UiState.LoggedOut).message, authViewModel

            )
        }

        is UiState.Success -> {
            HomeScreen(propertyViewModel, navigateTo)
        }

        is UiState.Loading -> {
            LoadingScreen()
        }

        else -> {
            Text("Error")
        }
    }
}

@Composable
fun LoginScreen(
    errorName: String, authViewModel: AuthViewModel

) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.8f), contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                Text(
                    text = "Sign In",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(bottom = 6.dp)

                )
                Icon(imageVector = Icons.Default.ExitToApp, contentDescription = null)
            }

            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OutlinedTextField(value = authViewModel.loginCredentials.email,
                    onValueChange = { authViewModel.updateLoginState(email = it) },
                    label = { Text("Email") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Email
                    ),
                    leadingIcon = {
                        Icon(imageVector = Icons.Default.Email, contentDescription = null)
                    })

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(value = authViewModel.loginCredentials.password,
                    onValueChange = { authViewModel.updateLoginState(password = it) },
                    label = { Text("Password") },
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Password
                    ),
                    leadingIcon = {
                        Icon(imageVector = Icons.Default.Lock, contentDescription = null)
                    })

                Text(
                    text = errorName,
                    color = Color.Red,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(top = 8.dp)
                )

                Button(
                    onClick = {
                        authViewModel.login()
                    }, modifier = Modifier
                        .height(64.dp)
                        .width(200.dp)
                        .padding(top = 16.dp)
                ) {
                    Text(
                        "Login", color = Color.White, style = MaterialTheme.typography.titleMedium

                    )
                }
            }
        }
    }
}
