package foliocontrol.android.foliocontrolandroid.ui.components.dialogs

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddBox
import androidx.compose.material.icons.filled.Apartment
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Garage
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.House
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Store
import androidx.compose.material.icons.filled.Villa
import androidx.compose.material.icons.filled.Warehouse
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import foliocontrol.android.foliocontrolandroid.ui.components.foliocomponents.DropDownMenuItem
import foliocontrol.android.foliocontrolandroid.ui.components.foliocomponents.FolioDropdown
import foliocontrol.android.foliocontrolandroid.ui.components.foliocomponents.FolioTextField
import foliocontrol.android.foliocontrolandroid.ui.viewModels.PropertyViewModel

val items = listOf(
    DropDownMenuItem(
        "Apartment",
        { Icon(Icons.Default.Apartment, contentDescription = "Apartment") },
        "Apartment"
    ),
    DropDownMenuItem(
        "House",
        { Icon(Icons.Default.House, contentDescription = "House") },
        "House"
    ),
    DropDownMenuItem(
        "Garage",
        { Icon(Icons.Default.Garage, contentDescription = "Garage") },
        "Garage"
    ),
    DropDownMenuItem(
        "Store",
        { Icon(Icons.Default.Store, contentDescription = "Store") },
        "Store"
    ),
    DropDownMenuItem(
        "Terraced House",
        { Icon(Icons.Default.Villa, contentDescription = "Terraced House") },
        "Terraced House"
    ),
    DropDownMenuItem(
        "Semi-detached",
        { Icon(Icons.Default.Home, contentDescription = "Semi-detached") },
        "Semi-detached"
    ),
    DropDownMenuItem(
        "Villa",
        { Icon(Icons.Default.Villa, contentDescription = "Villa") },
        "Villa"
    ),
    DropDownMenuItem(
        "Storage",
        { Icon(Icons.Default.Warehouse, contentDescription = "Storage") },
        "Storage"
    ),
    DropDownMenuItem(
        "Other",
        { Icon(Icons.Default.Info, contentDescription = "Other") },
        "Other"
    )
)

@Composable
fun AddPropertyDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    propertyViewModel: PropertyViewModel,
    offline: Boolean = false
) {
    Dialog(

        onDismissRequest = { onDismissRequest() },
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true,
            usePlatformDefaultWidth = false
        )
    ) {
        var expanded by remember { mutableStateOf(false) }

        if (!offline) {
            Column(
                modifier = Modifier.fillMaxWidth().verticalScroll(rememberScrollState())
            ) {
                Surface(
                    color = MaterialTheme.colorScheme.onPrimary,
                    contentColor = MaterialTheme.colorScheme.primary,
                    shape = MaterialTheme.shapes.large,
                    modifier = Modifier.fillMaxWidth().clip(MaterialTheme.shapes.medium)
                        .padding(16.dp)
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize().padding(16.dp)
                    ) {
                        Row {
                            Icon(
                                imageVector = Icons.Filled.AddBox,
                                contentDescription = "Adding Property"
                            )
                            Text(
                                text = "Adding Property",
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.Bold
                                ),
                                modifier = Modifier.padding(start = 16.dp, bottom = 16.dp)
                            )
                        }
                        FolioTextField(
                            true,
                            "Name",
                            propertyViewModel.addPropertyState.propertyName
                        ) {
                            propertyViewModel.handlePropertyAddEdit(
                                propertyName = it
                            )
                        }
                        FolioDropdown(
                            expanded = expanded,
                            toggleExpanded = { expanded = !expanded },
                            items = items,
                            label = "Type",
                            onItemSelect = { selectedItem ->
                                propertyViewModel.handlePropertyAddEdit(propertyType = selectedItem)
                            }
                        )
//
                        FolioTextField(true, "Street", propertyViewModel.addPropertyState.street) {
                            propertyViewModel.handlePropertyAddEdit(street = it)
                        }
                        FolioTextField(
                            true,
                            "Street Number",
                            propertyViewModel.addPropertyState.streetNumber
                        ) {
                            propertyViewModel.handlePropertyAddEdit(streetNumber = it)
                        }
                        FolioTextField(
                            true,
                            "Zip Code",
                            propertyViewModel.addPropertyState.zipCode
                        ) {
                            propertyViewModel.handlePropertyAddEdit(
                                zipCode = it
                            )
                        }
                        FolioTextField(true, "City", propertyViewModel.addPropertyState.city) {
                            propertyViewModel.handlePropertyAddEdit(city = it)
                        }
                        FolioTextField(
                            true,
                            "Country",
                            propertyViewModel.addPropertyState.country
                        ) {
                            propertyViewModel.handlePropertyAddEdit(
                                country = it
                            )
                        }
                        Spacer(modifier = Modifier.weight(1f))
                        Row(
                            modifier = Modifier.fillMaxWidth().padding(top = 16.dp)
                        ) {
                            Button(
                                onClick = { onDismissRequest() },
                                modifier = Modifier.fillMaxWidth().height(48.dp).weight(1f)
                                    .padding(end = 8.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Close,
                                    contentDescription = "Close"
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(text = "Dismiss")
                            }
                            Button(
                                onClick = {
                                    onConfirmation()
                                },
                                modifier = Modifier.fillMaxWidth().height(48.dp).weight(1f)
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
        } else {
            // You must be online to add a property to your portfolio, make such screen
            Card {
                Text(text = "You must be online to add a property to your portfolio")
                Button(onClick = { propertyViewModel.getData() }) {
                    Text(text = "Retry Getting Data")
                }
            }
        }
    }
}
