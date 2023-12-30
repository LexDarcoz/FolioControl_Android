package foliocontrol.android.foliocontrolandroid.ui.components.dialogs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Apartment
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DomainAdd
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import foliocontrol.android.foliocontrolandroid.domain.Property
import foliocontrol.android.foliocontrolandroid.ui.components.foliocomponents.DropDownMenuItem
import foliocontrol.android.foliocontrolandroid.ui.components.foliocomponents.FolioDropdown
import foliocontrol.android.foliocontrolandroid.ui.components.foliocomponents.FolioTextField

val items =
    listOf(
        DropDownMenuItem(
            "Apartment",
            icon = {
                Icon(
                    Icons.Default.Apartment,
                    contentDescription = "Apartment",
                    tint = MaterialTheme.colorScheme.onSurface,
                )
            },
            "Apartment",
        ),
        DropDownMenuItem(
            "House",
            icon = {
                Icon(
                    Icons.Default.House,
                    contentDescription = "House",
                    tint = MaterialTheme.colorScheme.onSurface,
                )
            },
            "House",
        ),
        DropDownMenuItem(
            "Garage",
            icon = {
                Icon(
                    Icons.Default.Garage,
                    contentDescription = "Garage",
                    tint = MaterialTheme.colorScheme.onSurface,
                )
            },
            "Garage",
        ),
        DropDownMenuItem(
            "Store",
            icon = {
                Icon(
                    Icons.Default.Store,
                    contentDescription = "Store",
                    tint = MaterialTheme.colorScheme.onSurface,
                )
            },
            "Store",
        ),
        DropDownMenuItem(
            "Terraced House",
            icon = {
                Icon(
                    Icons.Default.Villa,
                    contentDescription = "Terraced House",
                    tint = MaterialTheme.colorScheme.onSurface,
                )
            },
            "Terraced House",
        ),
        DropDownMenuItem(
            "Semi-detached",
            icon = {
                Icon(
                    Icons.Default.Home,
                    contentDescription = "Semi-detached",
                    tint = MaterialTheme.colorScheme.onSurface,
                )
            },
            "Semi-detached",
        ),
        DropDownMenuItem(
            "Villa",
            icon = {
                Icon(
                    Icons.Default.Villa,
                    contentDescription = "Villa",
                    tint = MaterialTheme.colorScheme.onSurface,
                )
            },
            "Villa",
        ),
        DropDownMenuItem(
            "Storage",
            icon = {
                Icon(
                    Icons.Default.Warehouse,
                    contentDescription = "Storage",
                    tint = MaterialTheme.colorScheme.onSurface,
                )
            },
            "Storage",
        ),
        DropDownMenuItem(
            "Other",
            icon = {
                Icon(
                    Icons.Default.Info,
                    contentDescription = "Other",
                    tint = MaterialTheme.colorScheme.onSurface,
                )
            },
            "Other",
        ),
    )

@Composable
fun AddPropertyDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    getData: () -> Unit = { },
    addPropertyState: Property,
    handlePropertyNameAddEdit: (String) -> Unit,
    handlePropertyTypeAddEdit: (String) -> Unit,
    handlePropertyStreetAddEdit: (String) -> Unit,
    handlePropertyStreetNumberAddEdit: (String) -> Unit,
    handlePropertyZipCodeAddEdit: (String) -> Unit,
    handlePropertyCityAddEdit: (String) -> Unit,
    handlePropertyCountryAddEdit: (String) -> Unit,
    offline: Boolean = false,
) {
    Dialog(
        onDismissRequest = { onDismissRequest() },
        properties =
            DialogProperties(
                dismissOnBackPress = true,
                dismissOnClickOutside = true,
                usePlatformDefaultWidth = false,
            ),
    ) {
        var expanded by remember { mutableStateOf(false) }
        Box(
            modifier =
                Modifier
                    .fillMaxSize()
                    .background(
                        brush =
                            Brush.verticalGradient(
                                colors =
                                    listOf(
                                        Color.Black.copy(alpha = 0.75f),
                                        Color.Black.copy(alpha = 0.75f),
                                    ),
                            ),
                    ),
            contentAlignment = Alignment.Center,
        ) {
            if (!offline) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Surface(
                        color = MaterialTheme.colorScheme.onPrimary,
                        contentColor = MaterialTheme.colorScheme.primary,
                        shape = MaterialTheme.shapes.large,
                        modifier =
                            Modifier
                                .fillMaxWidth()
                                .clip(MaterialTheme.shapes.medium)
                                .padding(16.dp)
                                .height(500.dp),
                    ) {
                        Column(
                            modifier =
                                Modifier
                                    .fillMaxSize()
                                    .padding(16.dp),
                        ) {
                            Row {
                                Icon(
                                    imageVector = Icons.Filled.DomainAdd,
                                    contentDescription = "Adding Property",
                                )
                                Text(
                                    text = "Adding Property",
                                    style =
                                        MaterialTheme.typography.titleMedium.copy(
                                            fontWeight = FontWeight.Bold,
                                        ),
                                    modifier = Modifier.padding(start = 16.dp, bottom = 16.dp),
                                )
                            }

                            FolioTextField(
                                true,
                                "Name",
                                addPropertyState.propertyName,
                            ) {
                                handlePropertyNameAddEdit(
                                    it,
                                )
                            }
                            FolioDropdown(
                                expanded = expanded,
                                toggleExpanded = { expanded = !expanded },
                                items = items,
                                label = "Type",
                                onItemSelect = { selectedItem ->
                                    handlePropertyTypeAddEdit(
                                        selectedItem,
                                    )
                                },
                            )

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                Box(modifier = Modifier.weight(1f)) {
                                    FolioTextField(
                                        true,
                                        "Street",
                                        addPropertyState.street,
                                    ) {
                                        handlePropertyStreetAddEdit(it)
                                    }
                                }

                                Spacer(modifier = Modifier.width(8.dp))
                                Box(modifier = Modifier.weight(1f)) {
                                    FolioTextField(
                                        true,
                                        "Street Number",
                                        addPropertyState.streetNumber,
                                    ) {
                                        handlePropertyStreetNumberAddEdit(it)
                                    }
                                }
                            }

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                Box(modifier = Modifier.weight(1f)) {
                                    FolioTextField(
                                        true,
                                        "Zip Code",
                                        addPropertyState.zipCode,
                                    ) {
                                        handlePropertyZipCodeAddEdit(
                                            it,
                                        )
                                    }
                                }
                                Spacer(modifier = Modifier.width(8.dp))
                                Box(modifier = Modifier.weight(1f)) {
                                    FolioTextField(
                                        true,
                                        "City",
                                        addPropertyState.city,
                                    ) {
                                        handlePropertyCityAddEdit(it)
                                    }
                                }
                            }

                            FolioTextField(
                                true,
                                "Country",
                                addPropertyState.country,
                            ) {
                                handlePropertyCountryAddEdit(
                                    it,
                                )
                            }
                            Spacer(modifier = Modifier.weight(1f))
                            Row(
                                modifier =
                                    Modifier
                                        .fillMaxWidth()
                                        .padding(top = 16.dp),
                            ) {
                                Button(
                                    onClick = { onDismissRequest() },
                                    modifier =
                                        Modifier
                                            .fillMaxWidth()
                                            .height(48.dp)
                                            .weight(1f)
                                            .padding(end = 8.dp),
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Close,
                                        contentDescription = "Close",
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(text = "Dismiss")
                                }
                                Button(
                                    onClick = {
                                        onConfirmation()
                                    },
                                    modifier =
                                        Modifier
                                            .fillMaxWidth()
                                            .height(48.dp)
                                            .weight(1f)
                                            .padding(start = 8.dp),
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Check,
                                        contentDescription = "Save",
                                    )
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
                    Button(onClick = { getData() }) {
                        Text(text = "Retry Getting Data")
                    }
                }
            }
        }
    }
}
