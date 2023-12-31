package foliocontrol.android.foliocontrolandroid.ui.screens.account

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.filled.WifiOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import foliocontrol.android.foliocontrolandroid.domain.User
import foliocontrol.android.foliocontrolandroid.ui.components.foliocomponents.FolioTextField

/**
 * Composable function representing the detailed view of the user's account information.
 * Users can edit their personal details such as name, last name, email, address, and country.
 *
 * @param userState The current state of the user, including user details.
 * @param handleUserSave A callback function to handle saving user information.
 * @param handleUserNameEdit A callback function to handle changes to the user's first name.
 * @param handleUserLastNameEdit A callback function to handle changes to the user's last name.
 * @param handleUserEmailEdit A callback function to handle changes to the user's email.
 * @param handleUserStreetEdit A callback function to handle changes to the user's street address.
 * @param handleUserStreetNumberEdit A callback function to handle changes to the user's street number.
 * @param handleUserZipCodeEdit A callback function to handle changes to the user's ZIP code.
 * @param handleUserCityEdit A callback function to handle changes to the user's city.
 * @param handleUserCountryEdit A callback function to handle changes to the user's country.
 * @param offline A flag indicating whether the application is in offline mode.
 * @param navigateTo A callback function to navigate to a specific destination.
 */
@Composable
fun AccountDetailScreen(
    userState: User,
    handleUserSave: () -> Unit,
    handleUserNameEdit: (String) -> Unit,
    handleUserLastNameEdit: (String) -> Unit,
    handleUserEmailEdit: (String) -> Unit,
    handleUserStreetEdit: (String) -> Unit,
    handleUserStreetNumberEdit: (String) -> Unit,
    handleUserZipCodeEdit: (String) -> Unit,
    handleUserCityEdit: (String) -> Unit,
    handleUserCountryEdit: (String) -> Unit,
    offline: Boolean,
    navigateTo: (Any?) -> Unit = {},
) {
    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        Column {
            Text(
                text = "Details:",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(8.dp),
            )

            Column(
                modifier =
                    Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState()),
            ) {
                Surface(
                    color = MaterialTheme.colorScheme.secondary,
                    contentColor = MaterialTheme.colorScheme.primary,
                    shape = MaterialTheme.shapes.small,
                    shadowElevation = 4.dp,
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                ) {
                    Column(
                        modifier =
                            Modifier
                                .fillMaxSize()
                                .padding(16.dp),
                    ) {
                        FolioTextField(!offline, "Name", userState.name) {
                            handleUserNameEdit(
                                it,
                            )
                        }
                        FolioTextField(!offline, "Last Name", userState.lastName) {
                            handleUserLastNameEdit(
                                it,
                            )
                        }
                        FolioTextField(!offline, "Email", userState.email) {
                            handleUserEmailEdit(it)
                        }
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Box(modifier = Modifier.weight(1f)) {
                                FolioTextField(!offline, "Street", userState.street) {
                                    handleUserStreetEdit(it)
                                }
                            }
                            Spacer(modifier = Modifier.width(8.dp))
                            Box(modifier = Modifier.weight(1f)) {
                                FolioTextField(
                                    !offline,
                                    "Street Number",
                                    userState.streetNumber,
                                ) {
                                    handleUserStreetNumberEdit(it)
                                }
                            }
                        }

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Box(modifier = Modifier.weight(1f)) {
                                FolioTextField(
                                    !offline,
                                    "Zip Code",
                                    userState.zipCode,
                                ) {
                                    handleUserZipCodeEdit(it)
                                }
                            }
                            Spacer(modifier = Modifier.width(8.dp))
                            Box(modifier = Modifier.weight(1f)) {
                                FolioTextField(!offline, "City", userState.city) {
                                    handleUserCityEdit(it)
                                }
                            }
                        }
                        FolioTextField(!offline, "Country", userState.country) {
                            handleUserCountryEdit(it)
                        }
                        Spacer(modifier = Modifier.weight(1f))
                        Button(
                            enabled = !offline,
                            onClick = {
                                handleUserSave()
                                navigateTo("Home")
                            },
                            modifier =
                                Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 16.dp),
                        ) {
                            if (offline) {
                                Row {
                                    Icon(
                                        Icons.Default.WifiOff,
                                        contentDescription = null,
                                        modifier = Modifier.padding(end = 8.dp),
                                    )
                                    Text(text = "Offline preview")
                                }
                            } else {
                                Row {
                                    Icon(
                                        Icons.Default.Save,
                                        contentDescription = null,
                                        modifier = Modifier.padding(end = 8.dp),
                                    )
                                    Text(text = "Save")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
