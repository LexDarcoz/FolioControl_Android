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
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import foliocontrol.android.foliocontrolandroid.ui.components.foliocomponents.FolioTextField
import foliocontrol.android.foliocontrolandroid.ui.viewModels.AccountViewModel

@Composable
fun AccountDetailScreen(
    accountViewModel: AccountViewModel,
    offline: Boolean,
    navigateTo: (Any?) -> Unit = {}
) {
    Box(
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        Column {
            Text(
                text = "Account details",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Column(
                modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState())
            ) {
                Surface(
                    color = MaterialTheme.colorScheme.secondary,
                    contentColor = MaterialTheme.colorScheme.primary,
                    shape = MaterialTheme.shapes.small,
                    shadowElevation = 4.dp,
                    modifier = Modifier.fillMaxWidth().padding(16.dp)

                ) {
                    Column(
                        modifier = Modifier.fillMaxSize().padding(16.dp)
                    ) {
                        FolioTextField(!offline, "Name", accountViewModel.user.name) {
                            accountViewModel.handleUserEdit(
                                name = it
                            )
                        }
                        FolioTextField(!offline, "Last Name", accountViewModel.user.lastName) {
                            accountViewModel.handleUserEdit(
                                lastName = it
                            )
                        }
                        FolioTextField(!offline, "Email", accountViewModel.user.email) {
                            accountViewModel.handleUserEdit(email = it)
                        }
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(modifier = Modifier.weight(1f)) {
                                FolioTextField(!offline, "Street", accountViewModel.user.street) {
                                    accountViewModel.handleUserEdit(street = it)
                                }
                            }
                            Spacer(modifier = Modifier.width(8.dp))
                            Box(modifier = Modifier.weight(1f)) {
                                FolioTextField(
                                    !offline,
                                    "Street Number",
                                    accountViewModel.user.streetNumber
                                ) {
                                    accountViewModel.handleUserEdit(streetNumber = it)
                                }
                            }
                        }

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(modifier = Modifier.weight(1f)) {
                                FolioTextField(
                                    !offline,
                                    "Zip Code",
                                    accountViewModel.user.zipCode
                                ) {
                                    accountViewModel.handleUserEdit(
                                        zipCode = it
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.width(8.dp))
                            Box(modifier = Modifier.weight(1f)) {
                                FolioTextField(!offline, "City", accountViewModel.user.city) {
                                    accountViewModel.handleUserEdit(city = it)
                                }
                            }
                        }
                        FolioTextField(!offline, "Country", accountViewModel.user.country) {
                            accountViewModel.handleUserEdit(
                                country = it
                            )
                        }
                        Spacer(modifier = Modifier.weight(1f))
                        Button(
                            enabled = !offline,
                            onClick = {
                                accountViewModel.handleUserSave()
                                navigateTo("Home")
                            },
                            modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp)
                        ) {
                            if (offline) {
                                Text(text = "Offline preview")
                            } else {
                                Text(text = "Save")
                            }
                        }
                    }
                }
            }
        }
    }
}
