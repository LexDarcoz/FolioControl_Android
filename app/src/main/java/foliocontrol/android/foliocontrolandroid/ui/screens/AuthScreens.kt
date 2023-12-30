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
import foliocontrol.android.foliocontrolandroid.domain.LoginCredentials
import foliocontrol.android.foliocontrolandroid.domain.Partnership
import foliocontrol.android.foliocontrolandroid.domain.Property
import foliocontrol.android.foliocontrolandroid.ui.viewModels.AuthViewModel
import foliocontrol.android.foliocontrolandroid.ui.viewModels.PropertyViewModel
import foliocontrol.android.foliocontrolandroid.ui.viewModels.common.LoadingScreen
import foliocontrol.android.foliocontrolandroid.ui.viewModels.common.UiState

@Composable
fun AuthScreen(
    loginUiState: UiState,
    getData: () -> Unit = { },
    getUserData: () -> Unit = {},
    toggleSearchBar: () -> Unit = { },
    isSearchBarEnabled: Boolean = false,
    filteredList: List<Property>,
    filterProperties: (String) -> Unit,
    propertyListState: List<Property>,
    uiState: UiState,
    addPropertyState: Property,
    handlePropertyNameEdit: (String) -> Unit,
    handlePropertyTypeEdit: (String) -> Unit,
    handlePropertyStreetAddEdit: (String) -> Unit,
    handlePropertyStreetNumberAddEdit: (String) -> Unit,
    handlePropertyZipCodeAddEdit: (String) -> Unit,
    handlePropertyCityAddEdit: (String) -> Unit,
    handlePropertyCountryAddEdit: (String) -> Unit,
    selectProperty: (Property) -> Unit,
    deleteProperty: (Int) -> Unit,
    togglePropertyAddDialog: () -> Unit,
    handlePropertyAdd: () -> Unit = {},
    currentPartnership: Partnership,
    isAddPropertyDialogOpen: Boolean,
    //login
    loginCredentials: LoginCredentials,
    updateLoginStateEmail: (String) -> Unit,
    updateLoginStatePassword: (String) -> Unit,
    login: () -> Unit,

    navigateTo: (Any?) -> Unit = {}
) {
    when (loginUiState) {
        is UiState.LoggedOut -> {
            LoginScreen(errorName = (loginUiState as UiState.LoggedOut).message,
                loginCredentials,
                { updateLoginStateEmail(it) },
                { updateLoginStatePassword(it) },
                { login() })
        }

        is UiState.Success -> {
            HomeScreen(getData = { getData() },
                toggleSearchBar = { toggleSearchBar() },
                isSearchBarEnabled = isSearchBarEnabled,
                filteredList = filteredList,
                filterProperties = { filterProperties(it) },
                propertyListState = propertyListState,
                uiState = uiState,
                addPropertyState = addPropertyState,
                handlePropertyNameEdit = { handlePropertyNameEdit(it) },
                handlePropertyTypeEdit = { handlePropertyTypeEdit(it) },
                handlePropertyStreetAddEdit = { handlePropertyStreetAddEdit(it) },
                handlePropertyStreetNumberAddEdit = {
                    handlePropertyStreetNumberAddEdit(
                        it
                    )
                },
                handlePropertyZipCodeAddEdit = { handlePropertyZipCodeAddEdit(it) },
                handlePropertyCityAddEdit = { handlePropertyCityAddEdit(it) },
                handlePropertyCountryAddEdit = { handlePropertyCountryAddEdit(it) },
                selectProperty = { selectProperty(it) },
                deleteProperty = { deleteProperty(it) },
                togglePropertyAddDialog = { togglePropertyAddDialog() },
                handlePropertyAdd = { handlePropertyAdd() },
                isAddPropertyDialogOpen = isAddPropertyDialogOpen,
                currentPartnership = currentPartnership,
                navigateTo = navigateTo
            )
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
    errorName: String,
    loginCredentials: LoginCredentials,
    updateLoginStateEmail: (String) -> Unit,
    updateLoginStatePassword: (String) -> Unit,
    login: () -> Unit
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
                OutlinedTextField(value = loginCredentials.email,
                    onValueChange = { updateLoginStateEmail(it) },
                    label = { Text("Email") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Email
                    ),
                    leadingIcon = {
                        Icon(imageVector = Icons.Default.Email, contentDescription = null)
                    })

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(value = loginCredentials.password,
                    onValueChange = { updateLoginStatePassword(it) },
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
                        login()
                    }, modifier = Modifier
                        .height(64.dp)
                        .width(200.dp)
                        .padding(top = 16.dp)
                ) {
                    Text(
                        "Login", style = MaterialTheme.typography.titleMedium

                    )
                }
            }
        }
    }
}
