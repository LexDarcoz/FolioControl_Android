package foliocontrol.android.foliocontrolandroid.ui.components.dialogs


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import foliocontrol.android.foliocontrolandroid.ui.components.FolioTextField
import foliocontrol.android.foliocontrolandroid.ui.viewModels.PropertyViewModel

@Composable
fun AddPropertyDialog(
    onDismissRequest: () -> Unit, onConfirmation: () -> Unit, propertyViewModel: PropertyViewModel
) {
    Dialog(

        onDismissRequest = { onDismissRequest() }, properties = DialogProperties(
            dismissOnBackPress = true, dismissOnClickOutside = true, usePlatformDefaultWidth = false
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {
            Surface(
                color = MaterialTheme.colorScheme.onPrimary,
                contentColor = MaterialTheme.colorScheme.primary,
                shape = MaterialTheme.shapes.large,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(MaterialTheme.shapes.medium)
                    .padding(16.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    Text(
                        text = "Add Property", style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold
                        ), modifier = Modifier.padding(bottom = 16.dp)
                    )
                    FolioTextField("Name", propertyViewModel.addPropertyState.propertyName) {
                        propertyViewModel.handlePropertyAddEdit(
                            propertyName = it
                        )
                    }
                    FolioTextField("Type", propertyViewModel.addPropertyState.propertyType) {
                        propertyViewModel.handlePropertyAddEdit(
                            propertyType = it
                        )
                    }
                    FolioTextField("Street", propertyViewModel.addPropertyState.street) {
                        propertyViewModel.handlePropertyAddEdit(street = it)
                    }
                    FolioTextField(
                        "Street Number", propertyViewModel.addPropertyState.streetNumber
                    ) {
                        propertyViewModel.handlePropertyAddEdit(streetNumber = it)
                    }
                    FolioTextField("Zip Code", propertyViewModel.addPropertyState.zipCode) {
                        propertyViewModel.handlePropertyAddEdit(
                            zipCode = it
                        )
                    }
                    FolioTextField("City", propertyViewModel.addPropertyState.city) {
                        propertyViewModel.handlePropertyAddEdit(city = it)
                    }
                    FolioTextField("Country", propertyViewModel.addPropertyState.country) {
                        propertyViewModel.handlePropertyAddEdit(
                            country = it
                        )
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp)
                    ) {
                        Button(
                            onClick = { onDismissRequest() },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(48.dp)
                                .weight(1f)
                                .padding(end = 8.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Close, contentDescription = "Close"
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(text = "Dismiss")
                        }
                        Button(
                            onClick = {
                                onConfirmation()
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(48.dp)
                                .weight(1f)
                                .padding(start = 8.dp)
                        ) {
                            Icon(imageVector = Icons.Default.Check, contentDescription = "Save")
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(text = "Save")
                        }
                    }
                }
            }
        }
    }
}
