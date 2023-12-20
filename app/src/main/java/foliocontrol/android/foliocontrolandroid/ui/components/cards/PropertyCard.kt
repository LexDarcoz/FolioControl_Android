package foliocontrol.android.foliocontrolandroid.ui.components.cards

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Apartment
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Garage
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.House
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Store
import androidx.compose.material.icons.filled.Villa
import androidx.compose.material.icons.filled.Warehouse
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import foliocontrol.android.foliocontrol_android.R
import foliocontrol.android.foliocontrolandroid.data.remote.common.Constants
import foliocontrol.android.foliocontrolandroid.domain.Property
import foliocontrol.android.foliocontrolandroid.ui.components.dialogs.DeleteDialog
import foliocontrol.android.foliocontrolandroid.ui.viewModels.PropertyViewModel
import foliocontrol.android.foliocontrolandroid.ui.viewModels.common.UiState

@Composable
fun PropertyCard(
    propertyViewModel: PropertyViewModel,
    property: Property,
    navigateTo: (Any?) -> Unit = {}

) {
    val imageUrl = Constants.PROPERTYPHOTOS_URL
    val openDeleteDialog = remember { mutableStateOf(false) }
    when {
        openDeleteDialog.value -> {
            if (propertyViewModel.uiState is UiState.Success) {
                DeleteDialog(
                    onDismissRequest = { openDeleteDialog.value = false },
                    onConfirmation = {
                        openDeleteDialog.value = false
                        propertyViewModel.handlePropertyDelete(property.propertyID)
                    },
                    confirmText = "Confirm",
                    dismissText = "Dismiss",
                    dialogTitle = "Delete Property",
                    dialogText = "Are you sure you want to delete ${property.propertyName}.",
                    icon = Icons.Default.Warning
                )
            } else {
                DeleteDialog(
                    onDismissRequest = { openDeleteDialog.value = false },
                    onConfirmation = {
                        openDeleteDialog.value = false
                        propertyViewModel.getData()
                    },
                    confirmText = "Retry Connection",
                    dismissText = "Dismiss",
                    dialogTitle = "Network Error",
                    dialogText = "You need to be online in order to delete properties.",
                    icon = Icons.Default.Info
                )
            }
        }
    }
    val propertyTypesIcons: Map<String, ImageVector> = mapOf(
        "Apartment" to Icons.Default.Apartment,
        "House" to Icons.Default.House,
        "Garage" to Icons.Default.Garage,
        "Store" to Icons.Default.Store,
        "Terraced House" to Icons.Default.Villa,
        "Semi-detached" to Icons.Default.Home,
        "Villa" to Icons.Default.Villa,
        "Storage" to Icons.Default.Warehouse,
        "Other" to Icons.Default.Info

    )
    Card(
        modifier = Modifier.padding(8.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        shape = MaterialTheme.shapes.small,
        colors = CardDefaults.cardColors(
            contentColor = MaterialTheme.colorScheme.primary,
            containerColor = MaterialTheme.colorScheme.secondary
        )

    ) {
        Box(
            modifier = Modifier.height(150.dp).fillMaxSize()
        ) {
            // Load property image here
            if (property.propertyImg == "null") {
                Image(
                    painter = rememberAsyncImagePainter(model = R.drawable.ic_default),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            } else {
                AsyncImage(
                    model = "$imageUrl/${property.propertyImg}",
                    contentDescription = "${property.propertyName} image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }

            // Rounded icon
            Icon(
                imageVector = propertyTypesIcons[property.propertyType] ?: Icons.Default.Home,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.secondary,
                modifier = Modifier.size(40.dp).align(Alignment.BottomStart).padding(8.dp)
            )
            IconButton(
                onClick = { openDeleteDialog.value = true },
                modifier = Modifier.align(Alignment.TopEnd),
                colors = IconButtonDefaults.iconButtonColors(
                    contentColor = MaterialTheme.colorScheme.onPrimary,
                    containerColor = MaterialTheme.colorScheme.primary
                )
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.size(40.dp).padding(8.dp)
                )
            }
        }
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = property.propertyName,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = "${property.street} ${property.streetNumber}, ${property.city} ${property.zipCode}", // ktlint-disable max-line-length
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                text = property.propertyType,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(top = 8.dp)
            )
            Button(
                onClick = {
                    Log.i("TEST", "PropertyCard:selected property: $property")
                    propertyViewModel.selectProperty(property)
                    navigateTo("PropertyDetail")
                },
                modifier = Modifier.fillMaxWidth().padding(top = 4.dp)
            ) {
                Text(text = "View Property")
            }
        }
    }
}
