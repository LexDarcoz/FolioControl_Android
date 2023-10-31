package foliocontrol.android.foliocontrolandroid.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import foliocontrol.android.foliocontrol_android.R
import foliocontrol.android.foliocontrolandroid.viewModels.AuthViewModel
import foliocontrol.android.foliocontrolandroid.viewModels.LoginUiState
import foliocontrol.android.foliocontrolandroid.viewModels.setEncryptedPreference
import kotlinx.coroutines.launch


@Composable
fun AuthScreen(authViewModel: AuthViewModel, navigateTo: (Any?) -> Unit = {}) {
// Main auth
    when (authViewModel.loginUiState) {
        is LoginUiState.LoggedOut -> {
            LoginScreen(
                errorName = (authViewModel.loginUiState as LoginUiState.LoggedOut).message,
                authViewModel
            )
        }

        is LoginUiState.Success -> {
            HomeScreen(authViewModel, navigateTo)
        }

        is LoginUiState.Loading -> {
            LoadingScreen()
        }

        else -> {
            Text("Error")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    errorName: String, authViewModel: AuthViewModel
) {
    val scope = rememberCoroutineScope()
    var state by remember { authViewModel.loginState }
    val context = LocalContext.current

    Box(
        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
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
                    modifier = Modifier
                        .padding(bottom = 6.dp)



                )
                Icon(imageVector = Icons.Default.ExitToApp, contentDescription = null)

            }



            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 6.dp
                ),
                colors = CardDefaults.cardColors(

                    contentColor = MaterialTheme.colorScheme.primary,
                    containerColor = MaterialTheme.colorScheme.secondary
                ),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    OutlinedTextField(value = state.email,
                        onValueChange = { authViewModel.updateLoginState(email = it) },
                        label = { Text("Email") },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email),
                        leadingIcon = {
                            Icon(imageVector = Icons.Default.Email, contentDescription = null)
                        })

                    Spacer(modifier = Modifier.height(16.dp))

                    OutlinedTextField(value = state.password,
                        onValueChange = { authViewModel.updateLoginState(password = it) },
                        label = { Text("Password") },
                        visualTransformation = PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password),
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
                            scope.launch {
                                val token = authViewModel.getToken()
                                if (token.token.isNotBlank()) {
                                    setEncryptedPreference(token.token, context)
                                    Toast.makeText(context, "Welcome!", Toast.LENGTH_SHORT).show()
                                } else {
                                    Toast.makeText(context, "Failed to log in", Toast.LENGTH_SHORT)
                                        .show()
                                }
                            }
                        }, modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp)
                            .padding(top = 16.dp)
                    ) {
                        Text("Login", color = Color.White)
                    }
                }
            }
        }
    }
}


@Composable
fun LoadingScreen() {
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
            Text("Loading...")
        }
    }
}
