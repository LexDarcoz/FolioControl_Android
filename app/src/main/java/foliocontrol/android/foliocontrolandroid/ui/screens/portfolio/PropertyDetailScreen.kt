import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import foliocontrol.android.foliocontrol_android.R
import foliocontrol.android.foliocontrolandroid.data.remote.common.Constants
import foliocontrol.android.foliocontrolandroid.ui.components.FolioTextField
import foliocontrol.android.foliocontrolandroid.ui.viewModels.PropertyViewModel

@Composable
fun PropertyDetailScreen(
    propertyViewModel: PropertyViewModel, navigateTo: (Any?) -> Unit = {}, offline: Boolean = false
) {
    val imageUrl = Constants.PROPERTYPHOTOS_URL;
    Box(
        modifier = Modifier.fillMaxSize()

    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            if (propertyViewModel.propertyState.propertyImg == "null") {
                AsyncImage(
                    model = "${imageUrl}/default.avif",
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.3f)
                )
            } else {
                AsyncImage(
                    model = "${imageUrl}/${propertyViewModel.propertyState.propertyImg}",
                    contentDescription = "${propertyViewModel.propertyState.propertyName} image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.3f)
                )
            }


            Surface(
                color = MaterialTheme.colorScheme.secondary,
                contentColor = MaterialTheme.colorScheme.primary,
                shape = MaterialTheme.shapes.medium,
                shadowElevation = 4.dp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)

            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    Text(
                        text = "Details - ${propertyViewModel.propertyState.propertyName}",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                    FolioTextField(!offline, "Name", propertyViewModel.propertyState.propertyName) {
                        propertyViewModel.handlePropertyEdit(
                            propertyName = it
                        )
                    }
                    FolioTextField(!offline, "Type", propertyViewModel.propertyState.propertyType) {
                        propertyViewModel.handlePropertyEdit(
                            propertyType = it
                        )
                    }
                    FolioTextField(!offline, "Street", propertyViewModel.propertyState.street) {
                        propertyViewModel.handlePropertyEdit(street = it)
                    }
                    FolioTextField(
                        !offline, "Street Number", propertyViewModel.propertyState.streetNumber
                    ) {
                        propertyViewModel.handlePropertyEdit(streetNumber = it)
                    }
                    FolioTextField(
                        !offline, "Zip Code", propertyViewModel.propertyState.zipCode,
                    ) {
                        propertyViewModel.handlePropertyEdit(
                            zipCode = it
                        )
                    }
                    FolioTextField(!offline, "City", propertyViewModel.propertyState.city) {
                        propertyViewModel.handlePropertyEdit(city = it)
                    }
                    FolioTextField(!offline, "Country", propertyViewModel.propertyState.country) {
                        propertyViewModel.handlePropertyEdit(
                            country = it
                        )
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    Button(
                        enabled = !offline, onClick = {
                            propertyViewModel.handlePropertySave()
                            navigateTo("Home")
                        }, modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp)
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


