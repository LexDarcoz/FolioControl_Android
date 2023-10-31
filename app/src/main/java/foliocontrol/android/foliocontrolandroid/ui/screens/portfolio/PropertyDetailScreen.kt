import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import foliocontrol.android.foliocontrol_android.R
import foliocontrol.android.foliocontrolandroid.viewModels.PropertyViewModel


@Composable
fun PropertyDetailScreen(
    propertyViewModel: PropertyViewModel = PropertyViewModel()
) {
    var state by propertyViewModel.propertyState

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_default), // Replace with your image resource
                contentDescription = "Property Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

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
                        text = "Property Details",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                    PropertyField("Name", state.propertyName) {
                        propertyViewModel.handlePropertyEdit(
                            propertyName = it
                        )
                    }
                    PropertyField("Type", state.propertyType) {
                        propertyViewModel.handlePropertyEdit(
                            propertyType = it
                        )
                    }
                    PropertyField("Street", state.street) {
                        propertyViewModel.handlePropertyEdit(street = it)
                    }
                    PropertyField("Street Number", state.streetNumber) {
                        propertyViewModel.handlePropertyEdit(streetNumber = it)
                    }
                    PropertyField("Zip Code", state.zipCode) {
                        propertyViewModel.handlePropertyEdit(
                            zipCode = it
                        )
                    }
                    PropertyField("City", state.city) {
                        propertyViewModel.handlePropertyEdit(city = it)
                    }
                    PropertyField("Country", state.country) {
                        propertyViewModel.handlePropertyEdit(
                            country = it
                        )
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    Button(
                        onClick = {
                            // Handle save button click
                        }, modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp)
                    ) {
                        Text(text = "Save")
                    }
                }
            }
        }
    }
}

@Composable
fun PropertyField(label: String, value: String, onValueChange: (String) -> Unit) {
    Column(
        modifier = Modifier.padding(vertical = 8.dp)
    ) {
        Text(text = label, style = MaterialTheme.typography.bodyMedium)
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = androidx.compose.ui.text.input.ImeAction.Done
            ),
            keyboardActions = KeyboardActions(onDone = {
                // Handle done action
            }),
            textStyle = MaterialTheme.typography.bodySmall,
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primary)
                .padding(8.dp)
        )
    }
}

@Preview
@Composable
fun PropertyDetailScreenPreview() {
    PropertyDetailScreen()
}
