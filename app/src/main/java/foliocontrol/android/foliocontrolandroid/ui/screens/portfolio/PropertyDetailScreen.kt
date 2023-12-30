import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import foliocontrol.android.foliocontrol_android.R
import foliocontrol.android.foliocontrolandroid.data.remote.common.Constants
import foliocontrol.android.foliocontrolandroid.domain.Property
import foliocontrol.android.foliocontrolandroid.ui.components.dialogs.items
import foliocontrol.android.foliocontrolandroid.ui.components.foliocomponents.FolioDropdown
import foliocontrol.android.foliocontrolandroid.ui.components.foliocomponents.FolioTextField

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun PropertyDetailScreen(
    //Save data
    saveProperty: () -> Unit = {},
    //Property state
    propertyState: Property,
    //EDITTING
    handlePropertyEditName: (String) -> Unit = {},
    handlePropertyEditType: (String) -> Unit = {},
    handlePropertyEditStreet: (String) -> Unit = {},
    handlePropertyEditStreetNumber: (String) -> Unit = {},
    handlePropertyEditZipCode: (String) -> Unit = {},
    handlePropertyEditCity: (String) -> Unit = {},
    handlePropertyEditCountry: (String) -> Unit = {},

    navigateTo: (Any?) -> Unit,

    offline: Boolean = false
) {
    val imageUrl = Constants.PROPERTYPHOTOS_URL
    Box(
        modifier = Modifier.fillMaxSize()

    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(
                    enabled = true, state = rememberScrollState()
                )
        ) {
            if (propertyState.propertyImg == "null") {
                Image(
                    painter = painterResource(id = R.drawable.ic_default),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.3f)
                        .aspectRatio(16f / 9f) // Adjust the aspect ratio as needed
                        .align(Alignment.CenterHorizontally)
                )
            } else {
                GlideImage(
                    model = "$imageUrl/${propertyState.propertyImg}",
                    contentDescription = "${propertyState.propertyName} image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(16f / 9f) // Adjust the aspect ratio as needed
                        .align(Alignment.CenterHorizontally)
                )
            }

            Surface(
                color = MaterialTheme.colorScheme.secondary,
                contentColor = MaterialTheme.colorScheme.primary,
                shape = MaterialTheme.shapes.small,
                shadowElevation = 4.dp,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)

            ) {
                var expanded by remember { mutableStateOf(false) }
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    Text(
                        text = "Details - ${propertyState.propertyName}",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                    FolioTextField(
                        !offline, "Name", propertyState.propertyName
                    ) {
                        handlePropertyEditName(
                            it
                        )
                    }
                    FolioDropdown(
                        expanded = expanded,
                        toggleExpanded = { expanded = !expanded },
                        items = items,
                        label = "Type",
                        onItemSelect = { selectedItem ->
                            handlePropertyEditType(selectedItem)
                        },
                        initialValue = propertyState.propertyType
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(modifier = Modifier.weight(1f)) {
                            FolioTextField(
                                !offline, "Street", propertyState.street
                            ) {
                                handlePropertyEditStreet(it)
                            }
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Box(modifier = Modifier.weight(1f)) {
                            FolioTextField(
                                !offline, "Street Number", propertyState.streetNumber
                            ) {
                                handlePropertyEditStreetNumber(it)
                            }
                        }
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(modifier = Modifier.weight(1f)) {
                            FolioTextField(
                                !offline, "Zip Code", propertyState.zipCode
                            ) {
                                handlePropertyEditZipCode(
                                    it
                                )
                            }
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Box(modifier = Modifier.weight(1f)) {
                            FolioTextField(
                                !offline, "City", propertyState.city
                            ) {
                                handlePropertyEditCity(it)
                            }
                        }
                    }
                    FolioTextField(
                        !offline, "Country", propertyState.country
                    ) {
                        handlePropertyEditCountry(
                            it
                        )
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    Button(
                        enabled = !offline, onClick = {
                            saveProperty()
                            navigateTo("Home")
                        }, modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp)
                    ) {
                        if (offline) {
                            Row {
                                Icon(
                                    Icons.Default.WifiOff,
                                    contentDescription = null,
                                    modifier = Modifier.padding(end = 8.dp)
                                )
                                Text(text = "Offline preview")
                            }
                        } else {
                            Row {
                                Icon(
                                    Icons.Default.Save,
                                    contentDescription = null,
                                    modifier = Modifier.padding(end = 8.dp)
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
