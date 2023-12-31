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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
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
import foliocontrol.android.foliocontrolandroid.ui.viewModels.common.LoadingScreen
import foliocontrol.android.foliocontrolandroid.ui.viewModels.common.UiState
import foliocontrol.android.foliocontrolandroid.ui.viewModels.common.WindowInfo
import foliocontrol.android.foliocontrolandroid.ui.viewModels.common.rememberWindowInfo

/**
 * Composable function representing the authentication screen of the Folio Control Android application.
 * It dynamically switches between the login screen, success screen (home), and loading screen based on the [UiState].
 *
 * @param loginUiState The current state of the authentication process.
 * @param getData A callback function to retrieve data.
 * @param toggleSearchBar A callback function to toggle the search bar.
 * @param isSearchBarEnabled A flag indicating whether the search bar is enabled.
 * @param filteredList The filtered list of properties based on the search criteria.
 * @param filterProperties A callback function to filter properties based on a search query.
 * @param propertyListState The list of properties to display.
 * @param uiState The current state of the UI.
 * @param addPropertyState The state of the property being added.
 * @param handlePropertyNameEdit A callback function to handle changes to the property name during property addition/editing.
 * @param handlePropertyTypeEdit A callback function to handle changes to the property type during property addition/editing.
 * @param handlePropertyStreetAddEdit A callback function to handle changes to the property street during property addition/editing.
 * @param handlePropertyStreetNumberAddEdit A callback function to handle changes to the property street number during property addition/editing.
 * @param handlePropertyZipCodeAddEdit A callback function to handle changes to the property zip code during property addition/editing.
 * @param handlePropertyCityAddEdit A callback function to handle changes to the property city during property addition/editing.
 * @param handlePropertyCountryAddEdit A callback function to handle changes to the property country during property addition/editing.
 * @param selectProperty A callback function to select a property from the list.
 * @param deleteProperty A callback function to delete a property.
 * @param togglePropertyAddDialog A callback function to toggle the property addition dialog.
 * @param handlePropertyAdd A callback function to handle the addition of a property.
 * @param currentPartnership The currently selected partnership.
 * @param isAddPropertyDialogOpen A flag indicating whether the property addition dialog is open.
 * @param loginCredentials The login credentials, including email and password.
 * @param updateLoginStateEmail A callback function to update the email in the login credentials.
 * @param updateLoginStatePassword A callback function to update the password in the login credentials.
 * @param login A callback function to perform the login.
 * @param navigateTo A callback function to navigate to a specific destination.
 */
@Composable
fun AuthScreen(
    loginUiState: UiState,
    getData: () -> Unit = { },
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
    // login
    loginCredentials: LoginCredentials,
    updateLoginStateEmail: (String) -> Unit,
    updateLoginStatePassword: (String) -> Unit,
    login: () -> Unit,
    navigateTo: (Any?) -> Unit = {},
) {
    when (loginUiState) {
        is UiState.LoggedOut -> {
            LoginScreen(
                errorName = loginUiState.message,
                loginCredentials,
                { updateLoginStateEmail(it) },
                { updateLoginStatePassword(it) },
                { login() },
            )
        }

        is UiState.Success -> {
            HomeScreen(
                getData = { getData() },
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
                        it,
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
                navigateTo = navigateTo,
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

/**
 * Composable function representing the login screen of the Folio Control Android application.
 *
 * @param errorName The error message to display.
 * @param loginCredentials The login credentials, including email and password.
 * @param updateLoginStateEmail A callback function to update the email in the login credentials.
 * @param updateLoginStatePassword A callback function to update the password in the login credentials.
 * @param login A callback function to perform the login.
 */
@Composable
fun LoginScreen(
    errorName: String,
    loginCredentials: LoginCredentials,
    updateLoginStateEmail: (String) -> Unit,
    updateLoginStatePassword: (String) -> Unit,
    login: () -> Unit,
) {
    val windowInfo = rememberWindowInfo()
    Box(
        modifier =
            Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
        contentAlignment = Alignment.TopCenter,
    ) {
        Column(
            modifier =
                Modifier
                    .verticalScroll(
                        enabled = true,
                        state = rememberScrollState(),
                    )
                    .fillMaxWidth(),
        ) {
            Row(
                modifier =
                    Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(top = if (windowInfo.screenWidthInfo is WindowInfo.WindowType.Compact) 64.dp else 0.dp)
                        .wrapContentHeight(),
            ) {
                Text(
                    text = "Sign In",
                    style = MaterialTheme.typography.titleLarge,
                )
                Icon(imageVector = Icons.Default.ExitToApp, contentDescription = null)
            }

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                OutlinedTextField(
                    value = loginCredentials.email,
                    onValueChange = { updateLoginStateEmail(it) },
                    label = { Text("Email") },
                    singleLine = true,
                    keyboardOptions =
                        KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Email,
                        ),
                    colors =
                        OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White,
                            focusedLabelColor = MaterialTheme.colorScheme.primary,
                            unfocusedLabelColor = MaterialTheme.colorScheme.onSurface,
                        ),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Email,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onSurface,
                        )
                    },
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = loginCredentials.password,
                    onValueChange = { updateLoginStatePassword(it) },
                    label = { Text("Password") },
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions =
                        KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Password,
                        ),
                    colors =
                        OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White,
                            focusedLabelColor = MaterialTheme.colorScheme.primary,
                            unfocusedLabelColor = MaterialTheme.colorScheme.onSurface,
                        ),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Lock,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onSurface,
                        )
                    },
                )

                Text(
                    text = errorName,
                    color = Color.Red,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(top = 8.dp),
                )

                Button(
                    onClick = {
                        login()
                    },
                    modifier =
                        Modifier
                            .height(64.dp)
                            .width(200.dp)
                            .padding(top = 16.dp),
                ) {
                    Text(
                        "Login",
                        style = MaterialTheme.typography.titleMedium,
                    )
                }
            }
        }
    }
}
